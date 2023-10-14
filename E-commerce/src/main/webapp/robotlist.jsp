<%@ page import="java.util.List" %>
<%@ page import="testWeb.vo.RobotInfo" %>
<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>robot</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>


<body>



    <div class="form-box" style="text-align: center;">
        <%
            List<RobotInfo> robotInfoList = (List<RobotInfo>) request.getAttribute("list");

            String name = null;
            String userid = null;
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
        <h2>user:<%=name  %></h2></br> <h2>Robot Info</h2></br>



        <table class="table table-bordered table -striped">
            <tr>
                <th>ID</th>
                <th>name</th>
                <th>type</th>
                <th>age</th>
                <th><a href="addRobot.jsp" type="button" class="btn btn-success btn-sm">add robot</a>
                    <a href="operate?method=exit" type="button"  class="btn btn-danger btn-sm">exit system</a>
                </th>
            </tr>



            <%
                for(int i=0; i<robotInfoList.size();++i) {
            %>
            <tr>
                <td><%=robotInfoList.get(i).getRobotinfoid() %></td>
                <td><%=robotInfoList.get(i).getName() %></td>
                <td><%=robotInfoList.get(i).getType() %></td>
                <td><%=robotInfoList.get(i).getAge() %></td>

                <td>
                    <a href="robotinfo?id=<%=robotInfoList.get(i).getRobotinfoid() %>&name=<%=robotInfoList.get(i).getName() %>" type="button" class="btn btn-primary btn-sm">activity</a>
                    <a href="addRobot.jsp?id=<%=robotInfoList.get(i).getRobotinfoid() %>&name=<%=robotInfoList.get(i).getName() %>&type=<%=robotInfoList.get(i).getType() %>&age=<%=robotInfoList.get(i).getAge() %>" type="button" class="btn btn-primary btn-sm">edit</a>
                    <a href="operate?method=delete&id=<%=robotInfoList.get(i).getRobotinfoid() %>" type="button" class="btn btn-sm btn-danger">delete</a>
                </td>

            </tr>
            <% } %>
        </table>



        <br>


    </div>




<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

</body>
</html>