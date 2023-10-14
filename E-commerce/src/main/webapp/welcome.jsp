<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>robot control system</title>
<style>
html{
height:100%;
width:100%;
background: url('https://zczrd.taiyuanxiji.com/static/img/login-background.f9f49138.jpg');
}
.cent{
color:#fff;
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
}
.flex-cu{
 display: flex;
 
}
.flex-cu a{
 display: inline-block;
 width:70px;
 text-align:center;
 color:#fff;
}
.flex-cu a:hover{
 display: inline-block;
 width:70px;
 text-align:center;
 color:green;
}
.welcome{
margin-top:100px;
font-size:28px;
margin-bottom:40px
}
</style>
</head>
<body >
<div class="cent">
<div class="welcome">
  <%String username = (String)session.getAttribute("username"); %>
    welcome  
     <%= username %>
</div>
<div class="flex-cu">
 
   <a  href="inquire.jsp">inquire</a>
   <a href="modify.jsp">modify</a>
   <a href="robotlist.jsp">robot</a></div>
</div>
 
</body>
</html>