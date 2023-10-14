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

public class UpdateServlet extends HttpServlet {
	public void doGet (HttpServletRequest req,HttpServletResponse res)
			 throws IOException,ServletException{
	}
			
	public void doPost (HttpServletRequest req,HttpServletResponse res)
			 throws IOException,ServletException{
			 
		     UserInfo userinfo = new UserInfo();
			 userinfo.setUsername(req.getParameter("username"));
			 userinfo.setPassword(req.getParameter("password"));
			 System.out.print(userinfo);
			 
			 
			 UserDAO dao = new UserDAOImpl();
			 int flag =0;	 
			 try {
			 flag = dao.updateUserInfo(userinfo);
			} catch(Exception e){
			// ToDo Auto - generated catch block 
			 e.printStackTrace();
			}
			 if (flag ==1){
			 HttpSession session =req.getSession ();
			 session.setAttribute("password",userinfo.getPassword());
			 session.setAttribute("username",userinfo.getUsername());
			 res.sendRedirect("./success2.jsp");
			} else{
			 }
}
}
