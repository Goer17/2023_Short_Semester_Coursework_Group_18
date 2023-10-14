package testWeb.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import testWeb.dao.RobotDAO;
import testWeb.dao.UserDAO;
import testWeb.dao.impl.RobotDAOImpl;
import testWeb.dao.impl.UserDAOImpl;
import testWeb.db.DBConnect;
import testWeb.vo.RobotInfo;
import testWeb.vo.UserInfo;

public class RobotServlet extends HttpServlet {
	public void doGet (HttpServletRequest req,HttpServletResponse res)
			 throws IOException,ServletException{
	}
			
	public void doPost (HttpServletRequest req,HttpServletResponse res)
			 throws IOException,ServletException{
			 RobotInfo robotinfo = new RobotInfo();
			 robotinfo.setName(req.getParameter("name"));
			 robotinfo.setType(req.getParameter("type"));
		
			 RobotDAO dao = new RobotDAOImpl();
			 int flag =0;
			 
			 try {
			 //flag = dao.queryByRobotInfo(robotinfo);
			} catch(Exception e){
			// ToDo Auto - generated catch block 
			 e.printStackTrace();
			}
	
			 if (flag ==1){
			 HttpSession session =req.getSession ();
			 session.setAttribute("name",robotinfo.getName());
			 session.setAttribute("type",robotinfo.getType());
			 res.sendRedirect("./robotinformation.jsp");
			} else{
			 
}
}
}