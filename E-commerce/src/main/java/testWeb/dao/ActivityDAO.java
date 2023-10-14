package testWeb.dao;

import testWeb.vo.Activity;
import testWeb.vo.RobotInfo;

import java.util.List;

public interface ActivityDAO {
	public List<RobotInfo> queryByRobotInfo (Integer robotid);
	public int updateRobotInfo(RobotInfo robotinfo);


	List<Activity> getActivityList();
}
