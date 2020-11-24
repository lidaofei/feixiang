package com.feixiang.rabbitmq.demo.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 简单生产者
 * @author lidaofei
 * @date 2020/11/25 7:23
 */
public class Producer {

    public static void main(String[] args) throws Exception {
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
        //创建频道
        Channel channel = connection.createChannel();
        //申明队列
        String queue_name = "simple_queue";
        channel.queueDeclare(queue_name,true,false,false,null);
        //发送消息
        String message = "hello rabbitmq";
        channel.basicPublish("",queue_name,null,message.getBytes());
        //释放资源
        channel.close();
        connection.close();
    }

}
