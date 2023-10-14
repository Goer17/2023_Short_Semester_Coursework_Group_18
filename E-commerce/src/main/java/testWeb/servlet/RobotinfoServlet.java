package testWeb.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import testWeb.dao.Robot2ActivityDAO;
import testWeb.dao.RobotDAO;
import testWeb.dao.UserDAO;
import testWeb.dao.impl.Robot2ActivityDAOImpl;
import testWeb.dao.impl.RobotDAOImpl;
import testWeb.dao.impl.UserDAOImpl;
import testWeb.vo.Activity;
import testWeb.vo.RobotInfo;
import testWeb.vo.UserInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RobotinfoServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String userid = req.getParameter("userid");

        Cookie[] cookies = req.getCookies();
        try {
            for (Cookie cookie : cookies) {
                if ("SID".equals(cookie.getName())) {
                    String sid = cookie.getValue();
                    String[] arr = sid.split("-");
                    userid = arr[1];
                    break;
                }
            }
        }catch (Exception e){
            res.sendRedirect("/login.jsp");
            return;
        }
        RobotDAO robotDAO = new RobotDAOImpl();
        boolean a = robotDAO.queryByUseridRobotid(Long.valueOf(userid),Long.valueOf(id));
        List<Activity> list = new ArrayList<>();
        if(a) {
            Robot2ActivityDAO dao = new Robot2ActivityDAOImpl();
            list = dao.queryActivityByRobotId(Long.valueOf(id));
        }
        req.setAttribute("list",list);
        req.setAttribute("robotid",id);
        req.setAttribute("name",name);
        req.getRequestDispatcher("/robotinformation.jsp").forward(req,res);



    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        doGet(req,res);

    }



}
