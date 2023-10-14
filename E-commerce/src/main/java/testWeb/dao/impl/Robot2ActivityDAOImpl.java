package testWeb.dao.impl;

import testWeb.dao.Robot2ActivityDAO;
import testWeb.dao.UserDAO;
import testWeb.db.DBConnect;
import testWeb.vo.Activity;
import testWeb.vo.UserInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Robot2ActivityDAOImpl implements Robot2ActivityDAO {

    @Override
    public List<Activity> queryActivityByRobotId(Long id) {
        String sql = "select activityid,ts,cls_id,cls,x1,x2,y1,y2,conf from activity   where activityid in (select activityid  from robot2activity where robotid=?)";
        //String sql = "SELECT t.activityid as activityid,t.num as num,t.type as type,t.map as map,t.time as time,vpic.pirurl as picurl FROM ( SELECT * FROM activity WHERE activity.activityid IN ( SELECT activityid FROM robot2activity WHERE robotid = ? ) ) t LEFT JOIN vpic ON t.activityid = vpic.activityid AND vpic.robotid = ? ";
        PreparedStatement pstmt = null;
        DBConnect dbc = null;
        List<Activity> list = new ArrayList<>();
        // 下面是针对数据库的具体操作
        try {
            // 连接数据库
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setLong(1, id);
            // 进行数据库查询操作
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Activity activity = new Activity();
                Integer activityid = rs.getInt("activityid");
                Timestamp ts = rs.getTimestamp("ts");
                Integer cls_id = rs.getInt("cls_id");
                String cls = rs.getString("cls");
                Float x1 = rs.getFloat("x1");
                Float y1 = rs.getFloat("y1");
                Float x2 = rs.getFloat("x2");
                Float y2 = rs.getFloat("y2");
                Float conf = rs.getFloat("conf");
                activity.setActivityid(Long.valueOf(activityid));
                activity.setCls(cls);
                activity.setConf(conf);
                if(ts !=null) {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String timeStr = df.format(ts);
                    activity.setTs(timeStr);
                }
                activity.setCls_id(cls_id);
                activity.setX1(x1);
                activity.setX2(x2);
                activity.setY1(y1);
                activity.setY2(y2);
                list.add(activity);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭数据库连接
            dbc.close();
        }

        for(int i=0,j=list.size();i<j;i++){
           List<String> picList = queryPicByRobotId(id,list.get(i).getActivityid());
           list.get(i).setPicList(picList);
        }

        return list;
    }

    @Override
    public boolean queryByRobotid(Long id, Long activityid) {
        String sql = "select * from robot2activity where robotid=? and activityid=?";
        PreparedStatement pstmt = null;
        DBConnect dbc = null;
        boolean flag = false;
        // 下面是针对数据库的具体操作
        try {
            // 连接数据库
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.setLong(2, activityid);
            // 进行数据库查询操作
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
               flag = true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭数据库连接
            dbc.close();
        }
        return flag;

    }

    @Override
    public Integer insert(Long robotid, Long activity) {
        int flag = 0;

        String sql = "insert into robot2activity(robotid,activityid) value (?,?)";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;

        // 下面是针对数据库的具体操作
        try{
            // 连接数据库
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            pstmt.setLong(1, robotid);
            pstmt.setLong(2, activity);

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


    public List<String> queryPicByRobotId(Long robotid, Long activityid) {
        String sql = "select picurl  from pic where robotid=? and activityid=?";
        PreparedStatement pstmt = null;
        DBConnect dbc = null;
        List<String> list = new ArrayList<>();
        // 下面是针对数据库的具体操作
        try {
            // 连接数据库
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setLong(1, robotid);
            pstmt.setLong(2, activityid);
            // 进行数据库查询操作
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Blob bb = rs.getBlob("picurl");
                if(bb != null){
                    InputStream pic = bb.getBinaryStream();
                    String pp = inputStream2Base64(pic);
                    list.add(pp);
                }
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭数据库连接
            dbc.close();
        }
        return list;
    }


    public static String inputStream2Base64(InputStream is){
        byte[] data = null;
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = is.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {

                }
            }
        }

        return Base64.getEncoder().encodeToString(data);
    }




}