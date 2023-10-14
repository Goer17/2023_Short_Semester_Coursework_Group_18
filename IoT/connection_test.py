import mysql.connector

# Test the connection

host_url = '192.168.8.106'

def main():
    demo_connection = mysql.connector.connect(
            host=host_url,
            user='lyy',
            passwd='!Lyy123456',
            database='javawebdb',
            auth_plugin='mysql_native_password'
        )

    if demo_connection.is_connected():
        print('Successfully connected!')
        cs = demo_connection.cursor()
        cs.execute('desc pic')
        for res in cs:
            print(res)

if __name__ == '__main__':
    main()