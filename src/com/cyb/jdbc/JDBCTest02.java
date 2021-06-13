package com.cyb.jdbc;

import java.sql.*;

/*
JDBC完成delete

使用注册驱动的第二种方式
 */
public class JDBCTest02 {
    public static void main(String[] args) {
        Statement stmt =  null;
        Connection conn = null;
        try {

            // 1. 注册驱动
            // 多态写法，父类型引用指向子类型对象。
            Class.forName("com.mysql.jdbc.Driver");

            // 2. 获取连接
            String url = "jdbc:mysql://localhost/bjpowernode";
            String user = "root";
            String password = "567cybtfboys";
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("数据库连接对象 =" + conn);

            // 3. 获取数据库操作对象(Statement专门执行sql语句的)
            stmt = conn.createStatement();

            // 4. 执行sql
            String sql =  "delete from dept where deptno = 70";
            // 专门执行DML语句的(insert delete update)
            // 返回值是”影响数据库中的记录条数“
            int count = stmt.executeUpdate(sql);
            System.out.println(count == 1? "保存成功" : "保存失败");

            // 5. 处理查询结果集 (这条语句是插入语句，不用处理查询结果集)

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            // 6. 释放资源
            // 为了保证资源一定释放，在finally语句块中关闭资源
            // 并且要遵循从小到大依次关闭
            // 分别对其进行try...catch
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
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
}
