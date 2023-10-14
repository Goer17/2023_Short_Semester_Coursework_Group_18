package testWeb.servlet;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import testWeb.dao.RobotDAO;
import testWeb.vo.*;
import testWeb.dao.UserDAO;
import testWeb.dao.impl.*;


public class UserLoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        UserInfo userinfo = new UserInfo();
        userinfo.setUsername(req.getParameter("username"));
        userinfo.setPassword(req.getParameter("password"));

        UserDAO dao = new UserDAOImpl();
        int flag = 0;

        try {
            flag = dao.queryByUserInfo(userinfo);
        } catch (Exception e) {
            // ToDo Auto - generated catch block
            e.printStackTrace();
        }
        if (flag != 0) {
            Cookie sidCookie = new Cookie("SID",userinfo.getUsername() + "-" + flag);
            sidCookie.setPath("/");
            sidCookie.setSecure(true);
            sidCookie.setMaxAge(3600);
            sidCookie.setHttpOnly(true);
            res.addCookie(sidCookie);
            res.sendRedirect("/robotlist");

        } else {
            res.sendRedirect("./error.jsp");
        }
    }
}
			 