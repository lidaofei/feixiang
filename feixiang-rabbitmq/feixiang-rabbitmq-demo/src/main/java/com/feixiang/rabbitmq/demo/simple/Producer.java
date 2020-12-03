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

    //参考
//    static final String QUEUE_NAME = "simple_queue";
//
//    public static void main(String[] args) throws Exception{
//        //创建连接工厂
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        //主机地址：默认为localhost
//        connectionFactory.setHost("192.168.13.111");
//        //连接端口
//        connectionFactory.setPort(5672);
//        //虚拟主机名称：默认为 /
//        connectionFactory.setVirtualHost("/itcast");
//        //连接用户名：默认为guest
//        connectionFactory.setUsername("heima");
//        //连接密码： 默认为guest
//        connectionFactory.setPassword("123456");
//        //创建连接
//        Connection connection = connectionFactory.newConnection();
//        //创建频道
//        Channel channel = connection.createChannel();
//        //声明（创建）队列
//        /*
//        参数一：队列名称
//        参数二：是否定义持久化队列
//        参数三：是否独占本次连接
//        参数四：是否在不使用的时候自动删除队列
//        参数五：队列其他参数
//         */
//        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
//        //要发送的消息
//        String message = "hello，小兔子，我来了！";
//        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
//        System.out.println("已发送消息："+message);
//        //释放资源
//        channel.close();
//        connection.close();
//    }
}
