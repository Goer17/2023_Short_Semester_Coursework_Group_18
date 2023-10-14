
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <script>



        function initSelect() {
            $.ajax({
                url: 'operate?method=getActivityList',
                type: 'get',
                //data: {companyName: ""},
                async: false,
                //dataType: 'json',
                success: function (result) {
                    console.log(result);
                    console.log(result.length);
                    if (result.length > 0) {
                        $("#activitys").html("");
                        for (var i = 0; i < result.length; i++) {
                            console.log(result[i].activityid);
                            $("#activitys").append($("<option value=\"" + result[i].activityid + "\">" + result[i].activityid + "</option>"));
                        }
                    }
                    if (result.length == 0) {
                        layer.msg('未查询到记录!', {time: 500});
                        html = '<div style="text-align:center;">未查询到记录</div>';
                        str = null;
                    }
                },
                error: function (data) {
                    str = null;
                    alert("AJAX错误数据" + data.status);

                }
            });
        }
    </script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>robot join activity</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="" rel="stylesheet">
</head>
<body onload="initSelect()">
<%
    String robotid = (String)request.getParameter("id") == null ? "" :   (String)request.getParameter("id");
    String robotname = (String)request.getParameter("name");
    String userid = null;
    String name;
    Cookie[] cookies = request.getCookies();
    try {
        for (Cookie cookie : cookies) {
            if ("SID".equals(cookie.getName())) {
                String sid = cookie.getValue();
                String[] arr = sid.split("-");
                name = arr[0];
                userid = arr[1];
                break;
            }
        }
    }catch (Exception e){

    }
%>
<div class="container">
    <h1>robot join activity</h1>
    <hr/>
    <form action="operate?method=addRobot2Activity" method="post" commandName="robot" role="form">

        <input type="text" class="form-control" id="id" name="robotid" value="<%=robotid%>" style="display:none"/>
        <input type="text" class="form-control" id="userid" name="userid" value="<%=userid%>" style="display:none"/>
        <div class="form-group>">
            <label for="name">name：</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="please input name" value="<%=robotname%>"/>
        </div>
        <label for="name">join activity id：</label>
        <select id="activitys" class="form-select" aria-label="Default select example" name="activityid" >
            <option selected>select activity id</option>
        </select>


        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">join</button>
        </div>
    </form>
</div>




</body>
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</html>
