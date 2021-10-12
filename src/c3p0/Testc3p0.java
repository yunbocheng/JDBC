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

/**
 * C3P0的演示
 *
 */
public class Testc3p0 {
    public static void main(String[] args) throws SQLException {
        /*
            传递有参数的创建方式，此时使用的数据库连接池参数是自定义的
            DataSource ds = new ComboPooledDataSource("customize");
        */
        // 1.创建数据库连接池对象(这个不传递参数，使用默认的数据库连接池参数)
        DataSource ds = new ComboPooledDataSource("customize");
        QueryRunner queryRunner = new QueryRunner(ds);
        Scanner scanner = new Scanner(System.in);
        // 2. 获取连接对象
        Connection connection = ds.getConnection();
        // 3. 执行sql语句

        // 增、删、改都可以使用update方法
        // 增加方法
        String sqlInsert = "insert into emp(emp_name,emp_salary) values(?,?)";
        // 添加数据
        Object[] objects = {"jury",60000};
        int update = queryRunner.update(sqlInsert, objects);
        if (update>0){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");
        }

        // 删除方法
        String sqlDelete = "delete from emp where emp_id = ?";
        System.out.println("请输入您要删除的id：");
        int i1 = scanner.nextInt();
        int update1 = queryRunner.update(connection, sqlDelete, new ArrayHandler(), i1);
        if(update1 > 0){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }

        //修改数据
        String sqlUpdate = "update emp set emp_name = ? , emp_salary = ? where emp_id = ? ";
        System.out.println("请输入您要删除的id：");
        int i2 = scanner.nextInt();
        int update2 = queryRunner.update(connection, sqlUpdate, new ArrayHandler(), i2);
        if(update2 > 0){
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");
        }

        // 查询一条数据的方法
        String sqlSelect1 = "select * from emp where emp_id = ?";
        // 使用这个方法只能查询出来数据库中的第一条信息
        System.out.println("请输入您要查询数据的id：");
        int i = scanner.nextInt();
        Object[] query = queryRunner.query(connection, sqlSelect1, new ArrayHandler(), i);
        for (Object o : query) {
            System.out.println(o);
        }

        // 查询数据库全部信息
        String sql2 = "select * from emp";
        // 使用这个方法可以查询出来数据库中的所有信息
        List<Object[]> query1 = queryRunner.query(sql2, new ArrayListHandler());
        for (Object[] objects1 : query1) {
            System.out.println(Arrays.toString(objects1) + " ");
        }

    }
}
