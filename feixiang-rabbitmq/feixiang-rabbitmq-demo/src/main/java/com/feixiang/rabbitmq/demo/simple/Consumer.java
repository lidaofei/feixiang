package com.feixiang.rabbitmq.demo.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 简单消费者
 * @author lidaofei
 * @date 2020/11/25 7:37
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //主机地址
        connectionFactory.setHost("localhost");
        //端口号
        connectionFactory.setPort(5672);
        //虚拟主机名称
        connectionFactory.setVirtualHost("/test");
        //连接用户名
        connectionFactory.setUsername("ldf");
        //连接密码
        connectionFactory.setPassword("123456");
        //创建连接
        Connection connection = connectionFactory.newConnection();

        //创建频道
        Channel channel = connection.createChannel();
        //创建队列
        String queueName = "simple_queue";
        channel.queueDeclare(queueName,true,false,false,null);
        //监听消息内部类
        DefaultConsumer consumer = new DefaultConsumer(channel){

        };
        //监听消息

    }
}
