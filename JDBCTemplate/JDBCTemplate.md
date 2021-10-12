## JDBCTemplate

- Spring框架中对JDBC的简单疯转。Spring提供了一个JDBCTemplate对象简化JDBC的开发。
- 这个依赖于连接池的jar包。
**步骤：**

1. 导入jar包（有好多的jar包）
2. 创建jdbcTemlpate对象。依赖于数据源DataSource
   - JdbcTemplate template = new jdbcTemplate(ds);
3. 调用JdbcTemplate的方法来完成CRUD的操作。
   - update()：执行DML语句。增、删、改语句

   - queryForMap()：查询结果。将查询结果集封装为Map集合。将列名作为key，将值作为value 将这条记录封装为一个map集合。

     **注意：这个方法查询的结果长度只能是1**

   - queryForList()：查询结果。将查询结果集封装到list集合

     **注意：每一条记录封装为一个Map集合，在将Map集合封装到List集合中。**

   - query()：查询结果。将查询结果集封装为JavaBean对象。
   - queryForObject()：查询结果。将结果封装为对象。

- **在实际开发中，最常见的是将查询的数据封装为JavaBean对象，然后将JavaBean对象封装在list集合中。**



**入门案例：**

![](https://gitee.com/YunboCheng/imageBad/raw/master/image/20211012170352.png)



**测试方法：**

![](https://gitee.com/YunboCheng/imageBad/raw/master/image/20211012170656.png)

**执行DML语句：**

-  修改信息信息

![image-20211012173542011](https://gitee.com/YunboCheng/imageBad/raw/master/image/image-20211012173542011.png)

- 添加一条消息

![image-20211012191819933](https://gitee.com/YunboCheng/imageBad/raw/master/image/image-20211012191819933.png)

- 删除数据

![image-20211012192006875](https://gitee.com/YunboCheng/imageBad/raw/master/image/image-20211012192006875.png)



**执行DQL语句：**

![](https://gitee.com/YunboCheng/imageBad/raw/master/image/20211012193124.png)

![](https://gitee.com/YunboCheng/imageBad/raw/master/image/20211012193259.png)

