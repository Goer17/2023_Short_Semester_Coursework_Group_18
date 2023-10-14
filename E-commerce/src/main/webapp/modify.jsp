<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>modify</title>
<style>
html{
height:100%;
width:100%;
background: url('https://zczrd.taiyuanxiji.com/static/img/login-background.f9f49138.jpg');
}
boby{
height:100%;
width:100%;

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
}
.form-item span{
display:block;
width:105px;
}
.form-input {
  width: 80%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}


.form-submit-button {
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.form-submit-button:hover {
  background-color: #45a049;
}
.form-submit-register{
  padding: 8px 20px;
  background-color: #eee;
  color: #000;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>

</head>
<body>
<div class="cent">
  <form  class="form-box" method="post" action="./update"  style="text-align: center;"> 
 <div  class="form-item"><span>username :</span>   <input class="form-input" type="text" name="username"/><br/></div>
	
 <div  class="form-item"><span>password :</span>  <input class="form-input" type="text" name="password" "/><br/></div>

	<input type="SUBMIT" name="xiugai" value="Submit"  class="form-submit-button"> 
    </form>  
</div>
    
</body>
</html>