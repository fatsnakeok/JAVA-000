package service.testDemo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2020/11/18 22:23
 * Copyright (c) 2020, zaodao All Rights Reserved.
 */
public class JdbcBatchHandleByStatement {



    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=FALSE&serverTimezone=UTC", "root", "rootroot");
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    private static HikariDataSource initDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/test?useSSL=FALSE&serverTimezone=UTC");
        config.setAutoCommit(false);
        config.setUsername("root");
        config.setPassword("rootroot");
        config.setConnectionTestQuery("SELECT 1;");
        config.setMaximumPoolSize(50);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setMaxLifetime(TimeUnit.MINUTES.toMillis(10L));
        config.setIdleTimeout(TimeUnit.MINUTES.toMillis(30L));

        return new HikariDataSource(config);
    }



    public static void main(String[] args) {
        String sql1 = "insert into tbl_user(id,name,password,email)" +
            "values(12,'xiongda','123','xiongda@qq.com')";

        String sql2 = "insert into tbl_address(id,city,country,user_id)" +
            "values(3,'hangzhou','china',10)";

//        Connection conn = getConnection();
        Connection conn = null;
        Statement st = null;
        try {
            conn = initDataSource().getConnection();
            conn.setAutoCommit(false);
            st = conn.createStatement();
            //添加要批量执行的SQL
            st.addBatch(sql1);
            st.addBatch(sql2);
            //执行批处理SQL语句
            st.executeBatch();
            //清除批处理命令
            st.clearBatch();
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
                    st.close();
                    conn.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }
}
