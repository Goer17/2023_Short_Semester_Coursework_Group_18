package testWeb.dao;

import java.util.List;

import testWeb.vo.*;

public interface UserDAO {
    public int queryByUserInfo(UserInfo userinfo) throws Exception;

    public int insertUserInfo(UserInfo userinfo) throws Exception;

    public List<UserInfo> userList(List<UserInfo> userinfo);

    public int updateUserInfo(UserInfo userinfo);
}
