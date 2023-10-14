<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>inquire</title>
<style>
html{
height:100%;
width:100%;
background: url('https://zczrd.taiyuanxiji.com/static/img/login-background.f9f49138.jpg');
}
.cent{

   display: flex;
   align-items: center;
   justify-content: center;
}
.form-box{
 background-color: #fff;
margin-top:100px;
border:1px solid #eee;
border-radius:10px;
width:400px;
padding:20px

}
.form-item{
display:flex;
height:60px;
}
</style>
</head>
<body>
<%String username = (String)session.getAttribute("username"); %>
<%String userinfoid = (String)session.getAttribute("userinfoid"); %>
<%String password = (String)session.getAttribute("password"); %>
        <div class='cent'>
         <form class="form-box" method="post" action="./update"  style="text-align: center;"> 
      <div class="form-item"> <span>username :</span>  <%= username %></div> 
       <div class="form-item"> <span>password :</span>   <%= password %></div> 
         
       </form></div>
   
  </body>
</html>