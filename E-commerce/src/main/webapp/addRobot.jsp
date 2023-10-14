
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>robot edit</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="" rel="stylesheet">
</head>
<body>
<%
    String id = (String)request.getParameter("id") == null ? "" :   (String)request.getParameter("id");
    String type = (String)request.getParameter("type") == null ? "" :   (String)request.getParameter("type");
    String name = (String)request.getParameter("name") == null ? "" :   (String)request.getParameter("name");
    String age = (String)request.getParameter("age") == null ? "" :   (String)request.getParameter("age");
%>
<div class="container">
    <% if(!"".equals(id)){%>
    <h1>modify robot</h1>
    <%}else{%>
    <h1>add robot</h1>
    <%}%>
    <hr/>
    <form action="operate?method=add" method="post" commandName="robot" role="form">

        <input type="text" class="form-control" id="id" name="id" value="<%=id%>" style="display:none"/>
        <div class="form-group>">
            <label for="name">name：</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="please input name" value="<%=name%>"/>


        </div>

        <div class="form-group">
            <label for="type">type:</label>
            <textarea class="form-control" id="type" name="type" rows="3" placeholder="please input type" ><%=type%></textarea>
        </div>
        <div class="form-group">
            <label for="age">age：</label>
            <input type="text" class="form-control" id="age" name="age" placeholder="please input age"value="<%=age%>"/>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">submit</button>
        </div>
    </form>
</div>




</body>
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</html>
