from ultralytics import YOLO
import datetime
import time
import cv2
from PIL import Image
import mysql.connector
import os
import re
import tkinter as tk

host_url = '192.168.8.106'
video_stream_url = 'http://192.168.8.1:8083/?action=stream'
model_url = './model/last.pt'

def get_param():
    root = tk.Tk()
    root.title("Start")

    window_width = 400
    window_height = 200
    screen_width = root.winfo_screenwidth()
    screen_height = root.winfo_screenheight()
    x = (screen_width - window_width) // 2
    y = (screen_height - window_height) // 2
    root.geometry(f"{window_width}x{window_height}+{x}+{y}")

    host_url_label = tk.Label(root, text="host_url:")
    host_url_label.pack()
    host_url_entry = tk.Entry(root)
    host_url_entry.insert(0, host_url)
    host_url_entry.pack()

    video_stream_label = tk.Label(root, text="video_stream_url:")
    video_stream_label.pack()
    video_stream_entry = tk.Entry(root)
    video_stream_entry.insert(0, video_stream_url)
    video_stream_entry.pack()

    def login():
        host_url = host_url_entry.get()
        video_stream_url = video_stream_entry.get()
        root.destroy()
        print(host_url, video_stream_url)

    login_button = tk.Button(root, text="Enter", command=login)
    login_button.pack()

    root.mainloop()


def current_time() -> str:
    return datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

class Timer_cap:
    def __init__(self):
        self.timer = time.time()
    
    def save(self, res, acid, path='./img/', interval=1):
        if time.time() - self.timer >= interval and len(res.boxes.conf) > 0 and max(res.boxes.conf) >= 0.5:
            img = Image.fromarray(res.plot())
            img.save(path + str(acid) + '.png')
            self.timer = time.time()


def main():
    get_param()

    model = YOLO(model_url)
    db_connection = mysql.connector.connect(
        host=host_url,
        user='lyy',
        passwd='!Lyy123456',
        database='javawebdb',
        auth_plugin='mysql_native_password'
    )
    cs = db_connection.cursor()
    # Clear 2 tables
    cs.execute('truncate table activity')
    cs.execute('truncate table pic')
    db_connection.commit()
    cap = cv2.VideoCapture(video_stream_url) # The URL of video stream http://192.168.8.1:8083/?action=stream 
    tcp = Timer_cap()
    acid = 0
    while cap.isOpened():
        success, frame = cap.read()
        if success:
            res = model.predict(frame, conf=0.5)[0]
            bx_info = res.boxes
            sz = len(bx_info.cls)
            for i in range(sz):
                acid += 1
                cs.execute('insert into activity values(%s, %s, %s, %s, %s, %s, %s, %s, %s)',
                    (acid,
                    current_time(),
                    int(bx_info.cls[i]),
                    res.names[int(bx_info.cls[i])],
                    float(bx_info.xyxyn[i][0]),
                    float(bx_info.xyxyn[i][1]),
                    float(bx_info.xyxyn[i][2]),
                    float(bx_info.xyxyn[i][3]),
                    float(bx_info.conf[i])
                    )
                )
                db_connection.commit()
            tcp.save(res, acid=acid)
            annotated_frame = res.plot()
            cv2.imshow('小车摄像头', annotated_frame)
            if cv2.waitKey(1) & 0xff == ord('q'):
                break
        else:
            break
    
    cap.release()
    cv2.destroyAllWindows()

    for img in os.listdir('./img'):
        acid = int(re.match(r'(\d+)', img).group(0))
        with open('./img/' + img, 'rb') as f:
            img_binary = f.read()
            cs.execute('insert into pic(id, robotid, activityid, picurl) values(%s, %s, %s, %s)', (acid, 1, acid, img_binary))
            db_connection.commit()

    db_connection.close()

if __name__ == '__main__':
    main()