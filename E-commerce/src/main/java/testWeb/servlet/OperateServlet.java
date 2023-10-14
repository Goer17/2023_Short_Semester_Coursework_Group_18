package testWeb.servlet;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import testWeb.dao.ActivityDAO;
import testWeb.dao.Robot2ActivityDAO;
import testWeb.dao.RobotDAO;
import testWeb.dao.impl.ActivityDAOImpl;
import testWeb.dao.impl.Robot2ActivityDAOImpl;
import testWeb.dao.impl.RobotDAOImpl;
import testWeb.vo.Activity;
import testWeb.vo.RobotInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OperateServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        String name = null;
        String userid =  null;
        Cookie[] cookies = req.getCookies();
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
            res.sendRedirect("/login.jsp");
            return;
        }

        String method = req.getParameter("method");
        switch(method){
            case "exit":
                exit(req,res);
                break;
            case "delete":
                delete(req,res,userid);
                break;

            case "add":
                add(req,res,userid);
                break;
             case "addRobot2Activity":
                 addRobot2Activity(req,res);
                break;
            case "getActivityList":
                getActivityList(req,res);
                break;


            default:
                break;
            }






    }

    private void exit(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Cookie[] cookies=req.getCookies();
        for(Cookie cookie:cookies){
            cookie.setMaxAge(0);
            cookie.setPath("/");
            res.addCookie(cookie);
        }
        res.sendRedirect("/login.jsp");

    }

    private void delete(HttpServletRequest req, HttpServletResponse res,String userid) throws IOException {

        String id = req.getParameter("id");
        RobotDAO robotDAO = new RobotDAOImpl();
        boolean flag = robotDAO.queryByUseridRobotid(Long.valueOf(userid),Long.valueOf(id));
        if(flag){
            robotDAO.delete(Long.valueOf(id));
        }
        res.sendRedirect("/robotlist");



    }

    private void add(HttpServletRequest req, HttpServletResponse res,String userid) throws IOException {
        String id = req.getParameter("id");

        String name = req.getParameter("name");
        String type = req.getParameter("type");
        String age = req.getParameter("age");
        RobotDAO robotDAO = new RobotDAOImpl();
        if(id == null || "".equals(id)){
            boolean flag = robotDAO.add(name, type, Long.valueOf(age), Long.valueOf(userid));
        }else {
            boolean flag = robotDAO.update(Long.valueOf(id),name, type, Long.valueOf(age), Long.valueOf(userid));
        }
        res.sendRedirect("/robotlist");



    }
    private void getActivityList(HttpServletRequest req, HttpServletResponse res) throws IOException {

        ActivityDAO dao = new ActivityDAOImpl();

        List<Activity> list = dao.getActivityList();

        String data = JSON.toJSONString(list);
        res.setCharacterEncoding("UTF-8");// 设置将字符以"UTF-8"编码输出到客户端浏览器
        PrintWriter out = res.getWriter();// 获取输出流
        // 将字符转换成字节数组，指定以UTF-8编码进行转换
        res.setHeader("Content-Type", "text/json");
        out.write(data);// 使用PrintWriter流向客户端输出字符
        out.flush();
        out.close();
    }

    private void addRobot2Activity(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String robotid = req.getParameter("robotid");

        String name = req.getParameter("name");
        String userid = req.getParameter("userid");
        String activityid = req.getParameter("activityid");
        RobotDAO robotDAO = new RobotDAOImpl();
        Robot2ActivityDAO robot2ActivityDAO = new Robot2ActivityDAOImpl();

        boolean flag = robotDAO.queryByUseridRobotid(Long.valueOf(userid),Long.valueOf(userid));
        boolean flag2 = robot2ActivityDAO.queryByRobotid(Long.valueOf(robotid),Long.valueOf(activityid));
        if(flag && !flag2) {
            robot2ActivityDAO.insert(Long.valueOf(robotid), Long.valueOf(activityid));
        }

        res.sendRedirect("/robotinfo?id="+ robotid + "&name="+name);



    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doGet(req,res);


    }
}