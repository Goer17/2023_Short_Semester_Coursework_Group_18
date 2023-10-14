<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>register</title>
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

<form method="post" action="./register" style="text-align: center;" class="form-box">
	<h2>register</h2>
<div class="form-item"><span>username :</span> <input class="form-input" type="text" name="username" onsubmit="return che(this.value);"/><br/></div>
<div class="form-item"><span>password :</span> <input class="form-input" type="password" name="password" onsubmit="return pwd(this.value);"/><br/></div>

	<input type="SUBMIT" name="submit" value="Submit" class="form-submit-button">

</form>
</div>


<script>
function che(name){
	if(name!=null){
		alert("username can not be null！");
		return;
	}
	
}
function pwd(pass){
	if(pass!=null){
	alert("password can not be null！");
	return;
	}
}

</script>
</body>
</html>