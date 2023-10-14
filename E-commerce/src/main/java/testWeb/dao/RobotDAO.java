package testWeb.dao;

import testWeb.vo.*;

import java.util.List;

public interface RobotDAO {
	public List<RobotInfo> queryByUserid (Integer userid);
	public int updateRobotInfo(RobotInfo robotinfo);
	boolean queryByUseridRobotid(Long userid,Long robotid);
	boolean delete(Long robotid);
	boolean add(String name,String type,Long age,Long userid);
	boolean update(Long id,String name,String type,Long age,Long userid);
}
