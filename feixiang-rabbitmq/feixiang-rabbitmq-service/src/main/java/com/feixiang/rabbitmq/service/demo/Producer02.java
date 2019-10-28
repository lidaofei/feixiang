package com.feixiang.rabbitmq.service.demo;

import com.rabbitmq.client.ConnectionFactory;

/*
1、声明队列
2、创建连接
3、创建通道
4、通道声明队列
5、制定消息
6、发送消息，使用默认交换机
*/
public class Producer02 {
    private final static String QUEUE = "queue";

    private static void main(String[] orgs){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("");
        connectionFactory.setPort(0);
        connectionFactory.setUsername("");
        connectionFactory.setPassword("");
        connectionFactory.setVirtualHost("");

    }

}
