package testWeb.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import testWeb.dao.UserDAO;
import testWeb.db.DBConnect;
import testWeb.vo.UserInfo;

public class UserDAOImpl implements UserDAO {

public int queryByUserInfo(UserInfo userinfo) throws Exception {
  int flag = 0;
  String sql = "select * from userinfo where username=? and password = ?";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;   
  
        // 下面是针对数据库的具体操作   
        try{   
            // 连接数据库   
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ; 
            pstmt.setString(1,userinfo.getUsername()) ;   
            pstmt.setString(2,userinfo.getPassword()) ;
            // 进行数据库查询操作
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                // 查询出内容，将其与用户提交的内容对比
				flag = Integer.parseInt(rs.getString("userinfoid"));

            }   
            rs.close() ; 
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            // 关闭数据库连接   
            dbc.close() ;   
        }   
  return flag;
 }

@Override
public int insertUserInfo(UserInfo userinfo) throws Exception {
	int flag = 0;

	  String sql = "insert into userinfo values (default,?,?)";
	        PreparedStatement pstmt = null ;   
	        DBConnect dbc = null;   
	  
	        // 下面是针对数据库的具体操作   
	        try{   
	            // 连接数据库   
	            dbc = new DBConnect() ;   
	            pstmt = dbc.getConnection().prepareStatement(sql) ; 
	            pstmt.setString(1, userinfo.getUsername());
	            pstmt.setString(2, userinfo.getPassword());

	            flag=pstmt.executeUpdate();
	            System.out.print("flag");
	            pstmt.close() ;   
	        }catch (SQLException e){   
	            System.out.println(e.getMessage());   
	        }finally{   
	            // 关闭数据库连接   
	            dbc.close() ;   
	        }   
	  return flag;
}

@Override
public List<UserInfo> userList(List<UserInfo> userinfo) {
	String sql = "select * from userinfo";
    PreparedStatement pstmt = null ;   
    DBConnect dbc = null;   

    // 下面是针对数据库的具体操作   
    try{   
        // 连接数据库   
        dbc = new DBConnect() ;   
        pstmt = dbc.getConnection().prepareStatement(sql) ; 
        // 进行数据库查询操作   
        userinfo = (List<UserInfo>) pstmt.executeQuery();
        
        pstmt.close() ;   
    }catch (SQLException e){   
        System.out.println(e.getMessage());   
    }finally{   
        // 关闭数据库连接   
        dbc.close() ;   
    }   
	return userinfo;
}

@Override
public int updateUserInfo(UserInfo userinfo) {
	int flag = 0;
//update userinfo set  
	  String sql = "update userinfo set username=?,password=?"+"where username='"+userinfo.getUsername()+"'";
	        PreparedStatement pstmt = null ;   
	        DBConnect dbc = null;   
	  
	        // 下面是针对数据库的具体操作   
	        try{   
	            // 连接数据库   
	            dbc = new DBConnect() ;   
	            pstmt = dbc.getConnection().prepareStatement(sql) ; 
	            pstmt.setString(1, userinfo.getUsername());
	            pstmt.setString(2, userinfo.getPassword());

	            flag=pstmt.executeUpdate();
	            System.out.print("flag");
	            pstmt.close() ;   
	        }catch (SQLException e){   
	            System.out.println(e.getMessage());   
	        }finally{   
	            // 关闭数据库连接   
	            dbc.close() ;   
	        }   
	  return flag;
}
}