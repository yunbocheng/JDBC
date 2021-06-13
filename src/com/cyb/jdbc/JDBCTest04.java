package com.cyb.jdbc;

import java.sql.*;

/*

处理查询结果集

 */
public class JDBCTest04 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;


        try {

            // 1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 2. 获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","567cybtfboys");

            // 3. 获取数据库对象
            stmt = conn.createStatement();

            // 4. 执行sql
            String sql = "select empno,ename,sal as a  from emp";
            /*
             注意 ：
                int executeUpdate(insert/update/delete)
                ResultSet executeQuery(select)
             */
            rs = stmt.executeQuery(sql); // 专门执行DQL语句的方法。

            // 5. 处理查询结果集

        /*    boolean flag1 = rs.next();
            System.out.println(flag1); // true

            // 下边这个if语句只会将数据库中第一行的数据取出。
            if(flag1) {
                // 光标指向的行有数据才为true，否则为false
                // 取数据
                // getString()方法的特点：不管数据库中的数据类型是什么，都已String的形式取出。

                // JDBC中所有的下标是从1开始的，而不是从0开始的
                // 其中数字1、2、3代表的第几列，1代表第一列。
                String empno = rs.getString(1);
                String ename = rs.getString(2);
                String sal = rs.getString(3);
                System.out.println(empno+","+ ename+","+sal);
            }

            // 下边的这个if语句会将数据库的第二行数据取出
            flag1 = rs.next();
            if(flag1){
                String empno = rs.getString(1);
                String ename = rs.getString(2);
                String sal = rs.getString(3);
                System.out.println(empno+","+ ename+","+sal);
            }
*/
            // 根据上边测试可以看出，应该才采用循环将数据全部取出。
           while (rs.next()){
               /* String empno = rs.getString(1);
                String ename = rs.getString(2);
                String sal = rs.getString(3);
                System.out.println(empno+","+ ename+","+sal);*/

               // 上边的代码使用的是列数1、2、3不健壮
               // 以下代码直接使用的 字段名，比较健壮
               // 这个不是以列的下标获取，以列的名字获取

               // 注意 ：此时将sal字段值改为了 a ,所有说此时要查询a
               // 列名称不是表中的列名称，是查询结果集中的列名称。
              /* String empno = rs.getString("empno");
               String ename = rs.getString("ename");
               String sal = rs.getString("a");
               System.out.println(empno+","+ ename+","+sal);*/

               // 这样输出的好处，可以直接给所有人的工资直接加100，方便

               int  empno = rs.getInt("empno");
               String ename = rs.getString("ename");
               double sal = rs.getDouble("a");

               // 这只是改边了输出的结果，数据库中的数据并没有改变，以为进行的是查询语句，不会对数据库中的数据进行修改。

               System.out.println(empno+","+ ename+","+(sal+100));
           }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        // 6. 释放资源
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
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
