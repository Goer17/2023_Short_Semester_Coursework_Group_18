👋 Hello! These are the source code files about IoT part.

On this coursework, IoT students are mainly responsible for：

- Using **OpenCV** module to connect the camera on small car that was designed by Telecom and catch each important frame ;

- Using **YOLO** technology for object recognition ;
- Using  **mysql-connector** module connect the database of E-commerce and writing SQL to upload object detection information to their database.

<img src="https://typora-1313035735.cos.ap-nanjing.myqcloud.com/img/2023-10-15-102412.png" alt="Flow chart" style="zoom:67%;" />



#### Install the necessary dependencies

Before you can run the Python script, you must install the necessary modules :

- `opencv-python`
- `ultralytics`
- `mysql-connector`

Quickly installing :

```shell
pip3 install requirements.txt
```



#### Directories

- `./model/` : Save training parameters (`.pt` files)
- `./img/` : Save the results when detecting the objects



### How to start ?

At first, your computer and the database host computer **MUST** connect the Wi-Fi of the small car.



#### Test the connection to database

```shell
python3 connection_test.py
```

![image-20231014112804121](https://typora-1313035735.cos.ap-nanjing.myqcloud.com/img/2023-10-14-032804.png)



#### Run the code

```shell
python3 main.py
```

![image-20231014112936546](https://typora-1313035735.cos.ap-nanjing.myqcloud.com/img/2023-10-14-032937.png)

Then input the correct *host url* and *video stream url* of the camera, after that, push the "Enter" button.

A simple GUI will be generated to show the object detection process :

![image-20231014113354601](https://typora-1313035735.cos.ap-nanjing.myqcloud.com/img/2023-10-14-033355.png)

At the end of the run, **information such as the identification image is automatically uploaded to the remote database** .
