package testWeb.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import testWeb.dao.UserDAO;
import testWeb.dao.impl.UserDAOImpl;
import testWeb.vo.UserInfo;

public class UserSelectServlet extends HttpServlet{
	public void doGet (HttpServletRequest req,HttpServletResponse res)
			 throws IOException,ServletException{
	}
			
	public void doPost (HttpServletRequest req,HttpServletResponse res)
			 throws IOException,ServletException{
			 List<UserInfo> userinfo =null;
			
			 System.out.print(userinfo);
			 UserDAO dao = new UserDAOImpl();
			 int flag =0;
			 
			 try {
				 userinfo = dao.userList(userinfo);
			} catch(Exception e){
			// ToDo Auto - generated catch block 
			 e.printStackTrace();
			}
			 if (flag ==1){
			 HttpSession session =req.getSession ();
			 session.setAttribute("userList",userinfo);
			} else{
			 }
}

}
