package testWeb.dao.impl;

import testWeb.dao.RobotDAO;
import testWeb.db.DBConnect;
import testWeb.vo.RobotInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RobotDAOImpl implements RobotDAO {

    public List<RobotInfo> queryByUserid(Integer userid) {
        int flag = 0;
        List<RobotInfo> list = new ArrayList<>();
        String sql = "select * from robotinfo where userid=?";
        PreparedStatement pstmt = null;
        DBConnect dbc = null;

        // 下面是针对数据库的具体操作
        try {
            // 连接数据库
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setLong(1, userid);
            ;
            // 进行数据库查询操作
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                RobotInfo robotInfo = new RobotInfo();
                // 查询出内容，将其与用户提交的内容对比
                String id = rs.getString("robotinfoid");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String age = rs.getString("age");
                robotInfo.setRobotinfoid(Long.valueOf(id));
                robotInfo.setName(name);
                robotInfo.setType(type);
                robotInfo.setAge(Integer.valueOf(age));
                list.add(robotInfo);
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭数据库连接
            dbc.close();
        }
        return list;
    }


    public List<RobotInfo> robotList(List<RobotInfo> robotinfo) {
        String sql = "select * from robotinfo";
        PreparedStatement pstmt = null;
        DBConnect dbc = null;

        // 下面是针对数据库的具体操作
        try {
            // 连接数据库
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            // 进行数据库查询操作
            robotinfo = (List<RobotInfo>) pstmt.executeQuery();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭数据库连接
            dbc.close();
        }
        return robotinfo;
    }


    @Override
    public int updateRobotInfo(RobotInfo robotinfo) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean queryByUseridRobotid(Long userid, Long robotid) {
        boolean flag = false;
        List<RobotInfo> list = new ArrayList<>();
        String sql = "select * from robotinfo where userid=? and robotinfoid =?";
        PreparedStatement pstmt = null;
        DBConnect dbc = null;

        // 下面是针对数据库的具体操作
        try {
            // 连接数据库
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setLong(1, userid);
            pstmt.setLong(2, robotid);
            ;
            // 进行数据库查询操作
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                flag = true;
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭数据库连接
            dbc.close();
        }
        return flag;
    }

    @Override
    public boolean delete(Long robotid) {
        boolean flag = false;
        List<RobotInfo> list = new ArrayList<>();
        String sql = "delete from robotinfo where robotinfoid =?";
        PreparedStatement pstmt = null;
        DBConnect dbc = null;

        // 下面是针对数据库的具体操作
        try {
            // 连接数据库
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setLong(1, robotid);
            ;
            // 进行数据库查询操作
            flag = pstmt.execute();

            pstmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭数据库连接
            dbc.close();
        }
        return flag;
    }

    @Override
    public boolean add(String name, String type, Long age, Long userid) {
        boolean flag = false;
        List<RobotInfo> list = new ArrayList<>();
        String sql = "insert into robotinfo(name,type,age,userid) values (?,?,?,?)";
        PreparedStatement pstmt = null;
        DBConnect dbc = null;

        // 下面是针对数据库的具体操作
        try {
            // 连接数据库
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, type);
            pstmt.setLong(3, age);
            pstmt.setLong(4, userid);
            ;
            // 进行数据库查询操作
            flag = pstmt.execute();

            pstmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭数据库连接
            dbc.close();
        }
        return flag;
    }

    @Override
    public boolean update(Long id, String name, String type, Long age, Long userid) {
        boolean flag = false;

        String sql = "UPDATE robotinfo set name=?,type=?,age=?,userid=? WHERE robotinfoid=?";
        PreparedStatement pstmt = null;
        DBConnect dbc = null;

        // 下面是针对数据库的具体操作
        try {
            // 连接数据库
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, type);
            pstmt.setLong(3, age);
            pstmt.setLong(4, userid);
            pstmt.setLong(5, id);
            ;
            // 进行数据库查询操作
            flag = pstmt.execute();

            pstmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭数据库连接
            dbc.close();
        }
        return flag;
    }

}





