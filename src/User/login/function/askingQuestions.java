package User.login.function;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*

这个代码存在 sql注入问题

实现功能 ：
  1. 需求 ： 模拟用户登录功能的实现。
  2. 业务描述 ：
     程序运行的时候，提供一个输入的入口，可以让用户输入用户名和密码
     用户输入用户名和密码之后，提交信息，java程序收集到用户信息
     java程序连接数据库验证用户名和密码是否合法。
     合法：显示登陆成功
     不合法 ： 显示登录失败
   3. 数据饿准备：
     在实际开发中，表的设计会使用专业的建模工具，这里使用powerDesigner

   4. 当前程序存在问题：
     用户名: fdsa
     密码: fdsa' or '1'='1
     登录成功
     这中现象被称为SQL注入（黑客经常使用）

   5. 导致SQL注入的根本原因是什么？
     用户输入的信息中含有sql语句的关键字，并且这些关键字参与sql语句的编译过程，
     导致sql语句的原意被扭曲，进而达到sql注入。

   6. 解决sql注入问题（solveTheProblem见）

 */
public class askingQuestions {
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
        Statement stmt = null;
        ResultSet rs = null;


        try {

            // 1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 2. 获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","567cybtfboys");

            // 3. 获取数据库操作对象
            stmt = conn.createStatement();

            // 4. 执行sql语句
            String sql = "select * from t_user where loginName = '"+loginName+"' and loginPwd = '"+loginPwd+"'";
            // 以上正好完成了sql语句饿拼接，以下代码的含义是：发送sql语句给DBMS，DBMS进行sql语句编译
            // 正好将用户提供给的“非法信息”编译进去，导致了原sql语句的含义扭曲了。

            rs = stmt.executeQuery(sql);

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
