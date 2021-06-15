package Affair;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
sql脚本 ：
  drop table if exists t_act;
  create table t_act(
     actno int,
     balance double(7,2) // 注意: ?表示有效数子的个数，2表示小数位的个数。
  );
  insert into t_act(actno,balance) values(111,20000);
  insert into t_act(actno,balance) values(222,0);

  commit;
  select * from t_act;

处理事务的三行重要代码 ？
    conn.setAutoCommit(false);
    conn.commit();
    conn.rollback();
 */
public class transferAccounts {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            // 1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","567cybtfboys");
            // 将自动提交机制改为手动提交机制
            conn.setAutoCommit(false);  // 开启事务
            // 3. 获取预编译的数据库操作对象
            String sql = "update t_act set balance = ? where actno = ?";
            ps = conn.prepareStatement(sql);
            // 给?传值
            ps.setDouble(1,10000);
            ps.setInt(2,111);
            int count = ps.executeUpdate();

            // 给?传值
            ps.setDouble(1,10000);
            ps.setInt(2,222);
            count += ps.executeUpdate();

            System.out.println(count ==2 ? "转账成功" : "转账失败");

            // 提交事务
            // 程序执行到这里说明以上程序没有异常，手动提交数据
            conn.commit();

            // 回滚事务
            if (conn != null) {
                try {
                    conn.rollback();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            // 4. 执行sql语句
            // 5. 处理查询结果集
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
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
