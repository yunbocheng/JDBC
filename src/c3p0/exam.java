package c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class exam {
    public static void main(String[] args) {
        DataSource ds = new ComboPooledDataSource("customize");
        QueryRunner queryRunner = new QueryRunner(ds);
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        try {
            // 2. 获取连接对象
            connection = ds.getConnection();
            // 3. 执行sql语句（查询某月入职员工）
            String sql = "select *  from  emp where  MONTH(emp_date) = ?";
            System.out.print("请输入要查询的月份：");
            int mouth = scanner.nextInt();
            List<Object[]> query = queryRunner.query(connection, sql, new ArrayListHandler(), mouth);
            for (Object[] objects : query) {
                System.out.println(Arrays.toString(objects) + " ");
            }
            //执行sql语句（查询所有员工的平均薪资）
            String sqlAvg = "SELECT AVG(emp_salary) FROM emp";
            System.out.println("所有员工的平均薪资");
            Object[] query1 = queryRunner.query(connection, sqlAvg, new ArrayHandler());
            for (Object o : query1) {
                System.out.println(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
