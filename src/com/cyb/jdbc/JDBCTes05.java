package com.cyb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
案例实现 ：
  这段代码演示 PreparedStatement实现数据的增删改。
 */
public class JDBCTes05 {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            // 1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","567cybtfboys");

            // 3. 获取预编译的数据库操作对象 (这个是对数据库进行增加数据)
           /* String sql = "insert into dept(deptno,dname,loc) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,60);
            ps.setString(2,"销售部");
            ps.setString(3,"上海");*/

          /*  // 以下语句是修改数据
            String sql = "update dept set dname = ?,loc = ?where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"研发一部");
            ps.setString(2,"北京");
            ps.setInt(3,60);*/

            // 以下代码块是删除数据
            String sql = "delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,60);

            // 4. 执行sql语句
            int count = ps.executeUpdate();
            System.out.println(count);
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
