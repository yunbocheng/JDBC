package utils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 这个程序两个任务
 *   第一 ：测试DBUtil是否好用
 *   第二 ：模糊查询怎么写
 */
public class JDBCTest06 {
    public static void main(String[] args) {
        Connection conn =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            // 注册驱动 加 获取连接 因为注册驱动在DBUtil类中的静态代码块
            // 只要DBUtil类加载一定会执行，且执行一次，下边代码调用DBUtil.getConnection()
            // 可以证明出一定进行了DBUtil类的加载。
            conn = DBUtil.getConnection();

            // 获取预编译的数据库操作对象

            // 以下是模糊查询错误写法。
         /*   String sql = "select ename from emp where ename like '_?%'";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"A");*/

            // 以下是迷糊查询正确写法
            String sql = "select ename from emp where ename like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"_A%");
            rs = ps.executeQuery();

            while (rs.next()){
                System.out.println(rs.getString("ename"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 释放资源
            DBUtil.close(conn,ps,rs);
        }
    }
}
