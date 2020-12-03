package com.feixiang.rabbitmq.demo.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lidaofei
 * @date 2020/11/26 6:58
 */
public class ConnectionUtils {

    public static Connection getConnection() throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //主机地址
        connectionFactory.setHost("localhost");
        //连接端口
        connectionFactory.setPort(5672);
        //虚拟主机名称
        connectionFactory.setVirtualHost("/test");
        //设置用户名
        connectionFactory.setUsername("ldf");
        //连接密码
        connectionFactory.setPassword("123456");
        //创建连接
        Connection connection = connectionFactory.newConnection();
        return connection;
    }
}
