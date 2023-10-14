package testWeb.dao;

import testWeb.vo.Activity;

import java.util.List;

public interface Robot2ActivityDAO {
    List<Activity> queryActivityByRobotId(Long id);
    boolean queryByRobotid(Long id,Long activityid);
    Integer insert(Long robotid,Long activity);
}
