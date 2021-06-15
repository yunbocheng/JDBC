package User.login.function;

/*

1. 解决 sql注入问题
    只要用户提供的信息不参与SQL语句的编译过程，问题就解决了
    即使用户提供的信息中含有SQL语句的关键字，但是没有参与编译，不起作用
    要想用户不参与sql语句的编译过程，那么必须使用java.sql.PreparedStatement
    PreparedStatement接口继承了java.mysql.Statement
    PreparedStatement是属于预编译的数据库操作对象
    PreparedStatement的原理 ：预先对SQL语句的框架进行编译，然后再给SQL语句传“值”
2. 测试结果 ：
   用户名: fdas
   密码: fdsa' or '1'= '1
   登录失败

3. 解决sql注入的关键是什么？
   用户提供饿信息中即使含有关键字，但是这些关键字不参与编译，不起作用。

 */
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class solveTheProblem {
    public static void main(String[] args) {

        // 初始化一个界面
        Map<String,String> userLoginInfo = initiUI();

        // 验证用户名和密码
        boolean loginSuccess = login(userLoginInfo);

        // 最后输出结果
        System.out.println(loginSuccess ? "登录成功" : "登录失败");
    }

    /**
     * 用户登录
     * @param userLoginInfo 用户登录信息
     * @return false表示失败，true表示失败。
     */
    private static boolean login(Map<String, String> userLoginInfo) {

        // 打标记意识
        boolean loginSuccess = false;
        // 单独定义变量
        String loginName = userLoginInfo.get("loginName");
        String loginPwd = userLoginInfo.get("logPwd");

        // JDBC代码
        Connection conn = null;
        PreparedStatement ps = null; // 这里使用PreparedStatement（预编译的数据库操作对象）
        ResultSet rs = null;


        try {

            // 1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 2. 获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","567cybtfboys");

            // 3. 获取预编译的数据库操作对象

            // sql语句的框架，其中一个? 表示一个占位符，一个?将来接收一个“值”
            // 注意：占位符不能用单引号括起来。
            String sql = "select * from t_user where loginName = ? and loginPwd = ?";

            // 程序执行到此处，会发送sql语句框子给DBMS，然后DBMS进行sql语句的预先编译。

            ps = conn.prepareStatement(sql);

            // 给占位符传值，（第一个占位符即 ? 下标是1，第二个 ? 的下表是2，JDBC中下表从1开始）
            ps.setString(1,loginName);
            ps.setString(2,loginPwd);
            // 4. 执行sql语句
            rs = ps.executeQuery();

            // 注意：因为这是的查询只有两种情况，一种是没有数据，直接返回false，
            // 另一种有数据，但是数据库中只有一条该数据，所以不用使用while，直接查询一次使用if即可。
            if(rs.next()){
                // 登陆成功
                loginSuccess =  true;
            }
            // 5. 处理查询结果集

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        finally {
            // 6. 释放资源
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
        return loginSuccess;
    }

    /**
     * 初始化用户界面
     * @return 用户输入的用户名和密码等登录信息
     */
    private static Map<String, String> initiUI() {
        Scanner s = new Scanner(System.in);
        System.out.print("用户名: ");
        String loginName = s.nextLine();
        System.out.print("密码: ");
        String loginPwd = s.nextLine();
        Map<String , String> userLoginInfo  = new HashMap<>();
        userLoginInfo.put("loginName",loginName);
        userLoginInfo.put("logPwd",loginPwd);
        return userLoginInfo;
    }
}
