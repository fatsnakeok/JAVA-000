import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2020/11/18 22:14
 * Copyright (c) 2020, zaodao All Rights Reserved.
 */
public class TransactionTest {
    public static Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=FALSE&serverTimezone=UTC","root","rootroot");
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
    public static void insertUser(Connection conn) throws SQLException {
        String sql="insert into tbl_user(id,name,password,email)"+
            "values(10,'xiongda','123','xiongda@qq.com')";
        Statement st=conn.createStatement();
        int count=st.executeUpdate(sql);
        System.out.println("向用户表插入了"+count+"条记录！");
    }
    public static void insertAddress(Connection conn) throws SQLException {
        String sql="insert into tbl_address(id,city,country,user_id)"+
            "values(1,'hangzhou','china',10)";
        Statement st=conn.createStatement();
        int count=st.executeUpdate(sql);
        System.out.println("向地址表插入了"+count+"条记录！");
    }
    public static void main(String[] args) {
        Connection conn =getConnection();
        try {
            conn.setAutoCommit(false);
            insertUser(conn);
            insertAddress(conn);
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
            }finally {
                try {
                    conn.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }
}
