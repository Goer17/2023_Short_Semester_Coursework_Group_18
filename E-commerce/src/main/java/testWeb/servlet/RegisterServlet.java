package testWeb.servlet;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import testWeb.dao.UserDAO;
import testWeb.dao.impl.UserDAOImpl;
import testWeb.vo.UserInfo;

public class RegisterServlet extends HttpServlet {
	public void doGet (HttpServletRequest req, HttpServletResponse res)
			 throws IOException, ServletException {
	}
			
	public void doPost (HttpServletRequest req,HttpServletResponse res)
			 throws IOException,ServletException{
			 UserInfo userinfo = new UserInfo();
			 userinfo.setUsername(req.getParameter("username"));
			 userinfo.setPassword(req.getParameter("password"));
			 //判断加在前面，
			 if(userinfo.getUsername().equals("")) {
					 res.sendRedirect("./error.jsp");
					return;
				}
				if(userinfo.getPassword().equals("")) {
					 res.sendRedirect("./error.jsp");
					return;
				}
			 System.out.print(userinfo);
			 UserDAO dao = new UserDAOImpl();
			 int flag =0;
			 
			 try {
			 flag = dao.insertUserInfo(userinfo);
			} catch(Exception e){
			// ToDo Auto - generated catch block 
			 e.printStackTrace();
			}
			 if (flag ==1){
			 HttpSession session =req.getSession ();
			 session.setAttribute("username",userinfo.getUsername());
			 session.setAttribute("password",userinfo.getPassword());

			 res.sendRedirect("./success.jsp");
			} else{
			 }
			 
			
}
}
