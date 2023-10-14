#include <Wire.h> 
#include <LiquidCrystal_I2C.h> 
#include<Servo.h>  
#include <Arduino.h>
#include "MsTimer2.h"

//4weels control;motors
#define M1 7 //left
#define M2 4   //right
#define pwmA 5 //left pwm
#define pwmB 6 //right pwm

//wifi***********************************************************************
#define SPEED_DUTY 40
#define COMM_STOP  'I'//stop
#define COMM_UP    'A'//forward
#define COMM_DOWN  'B'//backward
#define COMM_RIGHT 'D'//right
#define COMM_LEFT  'C'//left

int DUOJI_IO = 9; //left and right IO
int DUOJI_IO2=10; //up and down IO

//speed feedback
int FRONT_RIGHT_S_IO =  13; //rifht forward IO
int FRONT_LEFT_S_IO =  12; //left forward IO
#define FRONT_RIGHT_S_BIT digitalRead(FRONT_RIGHT_S_IO)
#define FRONT_LEFT_S_BIT digitalRead(FRONT_LEFT_S_IO)

int buffer[3];  //store the temporary data;control motor 0 1 2 3 4 commands;control the servo in angle from 0 to 180
int i;

//variables' definition
int timer_count;
unsigned int speed_count;//duty cycle counter of 50times per cycle
char front_left_speed_duty;      
char front_right_speed_duty;
char behind_left_speed_duty;
char behind_right_speed_duty;
unsigned int tick_5ms = 0;//5ms counter, as basic cycles of main functions
unsigned int tick_1ms = 0;//1ms counter, as basic counters of motors
unsigned int tick_500ms = 0;
unsigned int tick_3s = 0;/
unsigned int switch_flag = 0;

int ctrl_comm;
unsigned int continue_time = 0;
int rec_flag=0;
unsigned char serial_data=0;
long int Costtime=0;//serial time
Servo myservo;   //servo 2 in pin 9
Servo myservo2; //servo 1 in pin 10
//wifi***********************************************************************

//LCD
int red=0;
int blue=0;
int green=0;
char colour;

int pos=0;

LiquidCrystal_I2C lcd(0x27,16,2);
int sp0=0;
int sp1=80;
int sp2=140;
int sp3=80;

//Distances arrays
long distance1[5]={0,0,0,0,0};
long distance2[5]={0,0,0,0,0};
long distance3[5]={0,0,0,0,0};
long k=0;


void setup(){
Serial.begin(9600);
  pinMode(A0, OUTPUT);
  pinMode(A1, INPUT);
  pinMode(A2, OUTPUT);
  pinMode(A3, INPUT);
  pinMode(12, OUTPUT);
  pinMode(13, INPUT);

  pinMode(M1, OUTPUT);
  pinMode(M2, OUTPUT);

  pinMode(DUOJI_IO, OUTPUT);   // servo 2
  pinMode(DUOJI_IO2, OUTPUT);  //servo 1
  myservo.attach(DUOJI_IO);  // attaches the servo on pin 9 to the servo object
  myservo2.attach(DUOJI_IO2);  // attaches the servo on pin 10 to the servo object
  MsTimer2::set(1, flash);   // pause of setup funtion
  MsTimer2::start();   //timer starting
  stop();
  myservo.write(100); // left and right servo in angle of 100
  myservo2.write(110); //up and down servo in angle of 110

  lcd.init();                  // initialize LCD_1602A
  lcd.backlight();             //backlight LCD
}

void loop(){
  lcd.clear();
//servo rotating from 0 to 180
  int p=0;
  int postemp=0;
  p=pos/180;
  if(p==3){
    pos=180;
  }
  if(pos>=180){
    pos=pos+3;
    postemp=pos; 
    pos=abs(360-pos);
    Serial.println(pos);
    myservo.write(pos);	
    pos=postemp;

  }
  else if(pos<180){
    pos=pos+3;
    myservo.write(pos);	
    Serial.println(pos);
  }

  int i=k%3;
  distance1[i]=MeasureDistance1();
  distance2[i]=MeasureDistance2();
  distance3[i]=MeasureDistance3(); 

  //Far ahead, straight ahead
  if (distance1[i]>=20 && distance1[i]<=400){
    line();
  }

  else if(distance1[i] < 20 && distance1[i] > 10){
    if(distance2[i] > distance3[i]){
      turnLeft(1000);//turn left
    }
    else if (distance3[i]>distance2[i]){
      turnRight(1000);//turn right
    }
    else{
      turnRight(1000);
    }
  }
//if the robot get stucked
  if(distance1[0] == distance1[1] && distance1[1] == distance1[2] && distance2[0] == distance2[1] && distance2[1] == distance2[2]  && distance3[0] == distance3[1] && distance3[1] == distance3[2] ||distance1[i]<10){
    back(500);
    if(distance2[i] > distance3[i]){
      Left(400);
    }
    else if (distance3[i]>distance2[i]){
      Right(400);//turn right
    }
  }
  k++;
  
//********WIFI**********
  //delay(15000);//
  get_uartdate();//get commands 
  UartTimeoutCheck();    //timeout check 
//********WIFI**********


}

//distance of front
long MeasureDistance1(){

  long duration1, distance1;
  digitalWrite(A0, LOW);
  delayMicroseconds(2);
  digitalWrite(A0, HIGH);
  delayMicroseconds(10);
  digitalWrite(A0, LOW);
  duration1 = pulseIn(A1, HIGH);
  distance1 = duration1 /58;
  if(distance1>=150){
    distance1=0;
  }
  //Serial.println(distance1);
  lcd.setCursor(0,0);           
  lcd.print(distance1);
  return distance1;

}

//distance of left
long MeasureDistance2(){
  long duration2, distance2;
  digitalWrite(12, LOW);
  delayMicroseconds(2);
  digitalWrite(12, HIGH);
  delayMicroseconds(10);
  digitalWrite(12, LOW);
  duration2 = pulseIn(13, HIGH);
  distance2 = duration2 /58;
    if(distance2>=150){
    distance2=0;
  }
 // Serial.println(distance2);
  lcd.setCursor(5,0);
  lcd.print(distance2);
  return distance2;

}

//distance of right
long MeasureDistance3(){
  long duration3, distance3;
  digitalWrite(A2, LOW);
  delayMicroseconds(2);
  digitalWrite(A2, HIGH);
  delayMicroseconds(10);
  digitalWrite(A2, LOW);
  duration3 = pulseIn(A3, HIGH);
  distance3 = duration3 /58;
    if(distance3>=150){
    distance3=0;
  }
 // Serial.println(distance3);
  lcd.setCursor(10,0);
  lcd.print(distance3);
  return distance3;

}

//line
void line(){
  analogWrite(pwmA,sp1);
  digitalWrite(M1,LOW);
  analogWrite(pwmB,sp1);
  digitalWrite(M2,LOW);
  lcd.setCursor(0,1);           
  lcd.print("forward");
}

//back
void back(int time){
  analogWrite(pwmA,sp1);
  digitalWrite(M1,HIGH);
  analogWrite(pwmB,sp1);
  digitalWrite(M2,HIGH);
  lcd.setCursor(0,1);           
  lcd.print(" back");
  delay(time);
}

//left
void turnLeft(int time){
  analogWrite(pwmA,sp3);
  digitalWrite(M1,HIGH);//left reverse
  analogWrite(pwmB,sp2);
  digitalWrite(M2,LOW);//right 
  lcd.setCursor(0,1);           
  lcd.print(" left");
  delay(time);
}
void Left(int time){
  analogWrite(pwmA,sp0);
  digitalWrite(M1,HIGH);//left reverse
  analogWrite(pwmB,sp2);
  digitalWrite(M2,LOW);//right 
  lcd.setCursor(0,1);           
  lcd.print(" left");
  delay(time);
}

//right
void turnRight(int time){
  analogWrite(pwmA,sp2);
  digitalWrite(M1,LOW);//left 
  analogWrite(pwmB,sp3);
  digitalWrite(M2,HIGH);//right reverse
  lcd.setCursor(0,1);           
  lcd.print("right");
  delay(time);
}
void Right(int time){

  analogWrite(pwmA,sp2);
  digitalWrite(M1,LOW);//left 
  analogWrite(pwmB,sp0);
  digitalWrite(M2,HIGH);//right reverse
  lcd.setCursor(0,1);           
  lcd.print("right");
  delay(time);
}

//stop
void stop(int time){

  analogWrite(pwmA,sp0);
  digitalWrite(M1,HIGH);
  analogWrite(pwmB,sp0);
  digitalWrite(M2,HIGH);
  lcd.setCursor(0,1);           
  lcd.print("treasure!");
  delay(time);
}

//wifi*************************************************************************

void flash()
{
  speed_count++;
  if (speed_count >= 50)
  {
    speed_count = 0;
  }

}
//command decoding 
 void  communication_decode()
 {  

       if(buffer[0]== 0)
      {
               switch(buffer[1]) 
                           {
                             case 0: stop();break;
                             case 1: line(); break;   
                             case 2: back();break;
                             case 3: left(150);break;
                             case 4: right(150);break; 
      	                     default :  break;
                           }
      } 
    if(buffer[0] == 1)  
     {
        switch(buffer[1])
          {
              case 1: myservo2.write(buffer[2]); break;    // contro the angle of servo 1
              case 2: myservo.write(buffer[2]);  break;    // contro the angle of servo 2       
              default :  break;
           }
           delay(100);
     }  
}
 
//serial of receiving command 
void get_uartdate()
{
     if(Serial.available() > 0) 
 {     
        serial_data=Serial.read();
       if(rec_flag==0)
       {
        if(serial_data==0xff)
        {   
                 rec_flag=1;
                 i=0;
                 Costtime=0; 
        }
     }
     else
    {
        if(serial_data==0xff)
       {
         rec_flag=0;
        if(i==3)
        {
              communication_decode();//command decoding 
              i=0;  
        }
       }   
        else 
      {
         buffer[i]=serial_data;
         i++;
      }
       
    }

    }

}
void UartTimeoutCheck(void)
{
    if(rec_flag == 1)
    {
       //Nowtime = millis(); 
       Costtime++;  
      if(Costtime == 100000)
      {
           rec_flag = 0;
           Costtime=0; 
      }
    }
}

//motional funtions in wifi
void right(int power) {
  analogWrite(pwmA,power);
  digitalWrite(M1,LOW);//left 
  analogWrite(pwmB,power);
  digitalWrite(M2,HIGH);//right reverse
}
void left(int power) {
  analogWrite(pwmA,power);
  digitalWrite(M1,HIGH);//left reverse
  analogWrite(pwmB,power);
  digitalWrite(M2,LOW);//right 
}
void stop() {
  analogWrite(pwmA,sp0);
  digitalWrite(M1,HIGH);
  analogWrite(pwmB,sp0);
  digitalWrite(M2,HIGH);
}
void back() {
  analogWrite(pwmA,sp1);
  digitalWrite(M1,HIGH);
  analogWrite(pwmB,sp1);
  digitalWrite(M2,HIGH);
}


