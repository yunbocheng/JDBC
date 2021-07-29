## 1.概念

?serverTimezone=GMT

1.1 JDBC是什么

Java DataBase Connectivity (Java语言来连接数据库)

1.2 JDBC的本质什么

JDBC时SUN公司制定的一套接口(interface) java.sql*;(这个包下有很多的接口)

接口都有调用者和实现者。

面向接口调用、面向接口写实现类，这都属于面向接口的编程。

1.3 为什么要面型接口编程？

解耦合 ：降低程序的耦合度，提高程序的扩展力。

多态机制就是非常典型的 ：面向抽象编程。（不要面向具体编程）

建议 ：
````java
Animal a = new Cat();

Animal b = new Dog();
````

喂养方法 ：
```java
public void feed(Animal d){
    // 面向父类编程
        }
```

// 不建议
```java
Dog a = new Dog();
Cat c = new Cat();
```

1.4 为什么SUN公司制定了一套JDBC接口呢

![](https://gitee.com/YunboCheng/imageBad/raw/master/image/20210611153414.png)

![](https://gitee.com/YunboCheng/imageBad/raw/master/image/20210611154044.png)

1.5 JDBC的本质是什么？

一套接口

![](https://gitee.com/YunboCheng/imageBad/raw/master/image/20210611163553.png)

## JDBC编程六步 （需要背）

- 第一步 ：注册驱动(作用：告诉Java程序，即将要连接哪个数据库)
- 第二步 ：获取连接(表示JVM的进程和数据库进程之间的通道打开了，这属于进程之间的通信，重量级的。使用完之后一定要关闭)
- 第三步 ：获取数据库操作对象(专门执行sql语句的对象)
- 第四步 ：执行SQL语句(DQL、DML...)
- 第五步 ：处理查询结果集(只要当第四步执行的是select语句的时候，才有这五步处理查询结果集)
- 第六步 ：释放资源 (使用完资源之后一定要进行关闭资源。Java和数据库属于进程间的通信，开启之后一定要进行关闭)

url: 统一资源定位符（网络中某个资源的绝对路径）

https://www.baidu.com/这就是一个url

URL包括哪几个部分 :

- 协议
- IP
- PORT
- 资源名

http://182.61.200.7:80/index.html

http:// 通信协议
182.61.200.7  服务器IP地址
80  服务器上软件的端口
index.html 服务器上某个资源名

连接mysql数据库的url
jdbc:mysql://192.168.1.106:3306/bjpowernode

连接oracle数据库的url
jdbc:oracle:thin:@localhost:1521:orcl //orcl为数据库的SID
jdbc:mysql://  协议
192.168.1.106 本机的IP地址 (127.0.0.1或者localhost)
3306  数据库的端口号
bjpowernode  具体的数据库实例名

说明 ： localhost和127.0.0.1都是本机的IP地址

![](https://gitee.com/YunboCheng/imageBad/raw/master/image/20210611185458.png)

重点 ：使用DQL语句处理查询集的原理

![](https://gitee.com/YunboCheng/imageBad/raw/master/image/20210613113257.png)

注意 ：光标刚开始时指向0的位置，没有数据，而next判断的则是数据库的下一行还有没有数据。
