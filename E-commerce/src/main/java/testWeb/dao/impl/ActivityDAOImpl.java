package testWeb.dao.impl;

import testWeb.dao.ActivityDAO;
import testWeb.dao.UserDAO;
import testWeb.db.DBConnect;
import testWeb.vo.Activity;
import testWeb.vo.RobotInfo;
import testWeb.vo.UserInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAOImpl implements ActivityDAO {

	@Override
	public List<RobotInfo> queryByRobotInfo(Integer robotid) {
		return null;
	}

	@Override
	public int updateRobotInfo(RobotInfo robotinfo) {
		return 0;
	}

	@Override
	public List<Activity> getActivityList() {
		int flag = 0;
		String sql = "select * from activity";
		PreparedStatement pstmt = null ;
		DBConnect dbc = null;
		List<Activity> list =  new ArrayList<>();
		// 下面是针对数据库的具体操作
		try{
			// 连接数据库
			dbc = new DBConnect() ;
			pstmt = dbc.getConnection().prepareStatement(sql) ;
			// 进行数据库查询操作
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Activity ac = new Activity();
				// 查询出内容，将其与用户提交的内容对比
				Integer activityid = rs.getInt("activityid");
				Timestamp ts = rs.getTimestamp("ts");
				Integer cls_id = rs.getInt("cls_id");
				String cls = rs.getString("cls");
				Float x1 = rs.getFloat("x1");
				Float y1 = rs.getFloat("y1");
				Float x2 = rs.getFloat("x2");
				Float y2 = rs.getFloat("y2");
				Float conf = rs.getFloat("conf");
				ac.setActivityid(Long.valueOf(activityid));
				ac.setCls(cls);
				ac.setConf(conf);
				if(ts != null) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String timeStr = df.format(ts);
					ac.setTs(timeStr);
				}
				ac.setCls_id(cls_id);
				ac.setX1(x1);
				ac.setX2(x2);
				ac.setY1(y1);
				ac.setY2(y2);
				list.add(ac);
			}
			rs.close() ;
			pstmt.close() ;
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}finally{
			// 关闭数据库连接
			dbc.close() ;
		}
		return list;
	}
}