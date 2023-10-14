package testWeb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import testWeb.dao.Robot2ActivityDAO;
import testWeb.dao.RobotDAO;
import testWeb.dao.impl.Robot2ActivityDAOImpl;
import testWeb.dao.impl.RobotDAOImpl;
import testWeb.vo.Activity;
import testWeb.vo.RobotInfo;

import java.io.IOException;
import java.util.List;

public class RobotlistServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

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
        if(name == null || userid == null){
            res.sendRedirect("/login.jsp");
            return;
        }

        RobotDAO dao = new RobotDAOImpl();
        List<RobotInfo> robotInfoList = dao.queryByUserid(Integer.valueOf(userid));

        req.setAttribute("list",robotInfoList);
        req.getRequestDispatcher("/robotlist.jsp").forward(req,res);



    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        doGet(req,res);

    }



}
