<%@ page import="testWeb.vo.Activity" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Activity Info</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

</head>
<body>


<div class="form-box" style="text-align: center;">


    <%
        List<Activity> list = (List<Activity>) request.getAttribute("list");
        String robotid = (String) request.getAttribute("robotid");
        String name = (String) request.getAttribute("name");
    %>
    <h2>Robot name:<%=name%>
    </h2></br>
    <h2>Activity Info:</h2></br>


    <table class="table table-bordered table -striped">
        <tr>
            <th>ID</th>
            <th>ts</th>
            <th>cls</th>
            <th>cls_id</th>
            <th>x1</th>
            <th>y1</th>
            <th>x2</th>
            <th>y2</th>
            <th>picture</th>
            <th> <a href="addActivity.jsp?id=<%=robotid%>&name=<%=name%>" type="button" class="btn btn-primary btn-sm">join activity</a>
            <a href="/robotlist" type="button" class="btn btn-primary btn-sm">robot list</a></th>


        </tr>

        <%
            for (int i = 0; i < list.size(); ++i) {
        %>
        <tr>
            <td><%=list.get(i).getActivityid() %>
            </td>
            <td><%=list.get(i).getTs() %>
            </td>
            <td><%=list.get(i).getCls() %>
            </td>
            <td style="white-space: pre-wrap"><%=list.get(i).getCls_id() %>
            </td>
            <td><%=list.get(i).getX1() %>
            </td>
            <td><%=list.get(i).getY1() %>
            </td>
            <td><%=list.get(i).getX2() %>
            </td>
            <td><%=list.get(i).getY2() %>
            </td>

            <td>

            <%
             List<String> picList = list.get(i).getPicList();
                for(int m=0; m<picList.size();++m) {
            %>
                <a href="#" target="_blank">
                    <img src="data:image/jpeg;base64,<%=picList.get(m) %>" style="width: 100px; height: 100px;">

                </a>


            <% } %>











            </td>



        </tr>
        <% } %>
    </table>


    <br>


</div>


</body>
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</html>