package Affair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
JDBC事务机制 ：
  1. JDBC中的事务是自动提交的，什么是自动提交？
     只执行任意一条sql语句，则自动提交一次，这是JDBC默认的事务行为。
     但是在实际的业务中，通常都是N条DML语句共同联合完成的，必须保证他们这些DML语句在同一个事务中同时成功或者失败的。

  2. 以下程序先进行验证一下。
     验证结果 ：
       JDBC中只要执行任意一条DML语句，就提交一次。

 */
public class JDBCTest06 {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            // 1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","567cybtfboys");

            // 3. 获取预编译的数据库操作对象
            String sql = "update dept set dname = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"x部门");
            ps.setInt(2,30);
            int count  = ps.executeUpdate(); // 执行第一条update语句


            // 重新给占位符
            ps.setString(1,"y部门");
            ps.setInt(2,20);
            int count1 = ps.executeUpdate(); // 执行第二条update语句
            // 4. 执行sql语句
            // 5. 处理查询结果集
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 6. 释放资源
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
