package utils;

import sun.util.resources.zh.TimeZoneNames_zh_CN;

import java.sql.*;


/*
JDBC工具类，简化JDBC编程。
将复杂的代码封装在一个类中，下次书写直接调用该类即可
例如：
  1.将注册驱动封装在静态代码块中，只要加载该类，一定进行静态代码块的执行，并且执行一次。
    因为执行其他代码的时候，只需要进行一次注册驱动即可。
  2.将获取连接的方法封装在方法中，直接使用类名.方法名调用即可，不需要多次书写复杂的代码。

 */
public class DBUtil {

    /**
     * 工具类中的方法都是私有的。
     * 因为工具类当中的方法都是静态的，不需要new对象，，直接采用类名调用。
     *
     */
    private DBUtil(){};

    // 静态代码块在类加载时执行，并且只执行一次。
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *获取数据库连接对象
     * @return 返回数据库连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","567cybtfboys");
    }

    /**
     *
     * @param conn 连接对象
     * @param ps 数据库操作对象
     * @param rs 结果集
     */
    public  static void close(Connection conn, Statement ps, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
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
