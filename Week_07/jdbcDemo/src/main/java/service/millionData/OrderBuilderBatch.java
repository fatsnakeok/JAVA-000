package service.millionData;

import common.IdWorker;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2020/11/30 13:37
 * Copyright (c) 2020, zaodao All Rights Reserved.
 */
public class OrderBuilderBatch {

    /**
     * IDWorker默认
     */
    private static IdWorker idWorker = new IdWorker();

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=FALSE&serverTimezone=UTC", "root", "12341234");
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static void insertUser(Connection conn) throws SQLException {
        conn.setAutoCommit(false);
        String sql = "insert into pay_order(order_code,goods_id,goods_name,orgin_price,sale_price,create_user,create_time,state)" +
                "VALUES(?,?,?,?,?,?,now(),?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        for (int i = 0; i < 1000000; i++) {
            statement.setLong(1, idWorker.nextId());
            statement.setString(2, "24008");
            statement.setString(3, "商品"+i);
            statement.setFloat(4, 100.00f);
            statement.setFloat(5, 99.00f);
            statement.setString(6, "24008");
            statement.setInt(7, '1');
            statement.addBatch();
        }
        int [] counts = statement.executeBatch();
    }

    //    public static void insertAddress(Connection conn) throws SQLException {
//        String sql="insert into tbl_address(id,city,country,user_id)"+
//                "values(1,'hangzhou','china',10)";
//        Statement st=conn.createStatement();
//        int count=st.executeUpdate(sql);
//        System.out.println("向地址表插入了"+count+"条记录！");
//    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();    //获取开始时间
        Connection conn = getConnection();
        try {
            conn.setAutoCommit(false);
            insertUser(conn);
//            insertAddress(conn);
            conn.commit();
        } catch (SQLException e) {
            System.out.println("************事务处理出现异常***********");
            e.printStackTrace();
            try {
                conn.rollback();
                System.out.println("*********事务回滚成功***********");
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } finally {
            long endTime = System.currentTimeMillis();    //获取结束时间
            System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        }
    }

}
