package com.feixiang.rabbitmq.demo.simple;

import com.rabbitmq.client.*;

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
        //监听消息内部类,(重写 DefaultConsumer#handleDelivery方法)
        DefaultConsumer consumer = new DefaultConsumer(channel){

            /*
            consumerTag ：消息者标签，在channel.basicConsume时候可以指定
            envelope: 消息包内容，可从中获取消息id，消息routingkey，交换机，消息和重转标记（收到消息失败后是否需要重新发送）
            properties: 消息属性
            body： 消息
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("路由key为："+envelope.getRoutingKey());
                System.out.println("交换机为:"+envelope.getExchange());
                System.out.println("消息id为:"+envelope.getDeliveryTag());
                System.out.println("接收到的消息："+ new String(body,"UTF-8"));
                System.out.println("=========================================");
            }
        };
        //监听消息
        channel.basicConsume(queueName,true,consumer);
    }

    //参考
//    public static void main(String[] args) throws Exception {
//        //获取连接
//        Connection connection = ConnectionUtil.getConnection();
//        //创建频道
//        Channel channel = connection.createChannel();
//        //创建队列：并设置消息处理
//        channel.queueDeclare(Producer.QUEUE_NAME,true,false,false,null);
//        //监听消息
//        DefaultConsumer consumer = new DefaultConsumer(channel) {
//            @Override
//            /*
//            consumerTag ：消息者标签，在channel.basicConsume时候可以指定
//            envelope: 消息包内容，可从中获取消息id，消息routingkey，交换机，消息和重转标记（收到消息失败后是否需要重新发送）
//            properties: 消息属性
//            body： 消息
//             */
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                //路由key
//                System.out.println("路由key为：" + envelope.getRoutingKey());
//                //交换机
//                System.out.println("交换机为：" + envelope.getExchange());
//                //消息id
//                System.out.println("消息id为：" + envelope.getDeliveryTag());
//                //收到的消息
//                System.out.println("接收到的消息：" + new String(body, "UTF-8"));
//                System.out.println("");
//                System.out.println("================================================================");
//                System.out.println("");
//            }
//        };
//        /*
//        监听消息
//        参数一：队列名称
//        参数二：是否自动确认，设置为true表示消息接收到自动向mq回复接收到了，mq接收到回复后会删除消息；设置为false则需要手动确认
//         */
//        channel.basicConsume(Producer.QUEUE_NAME, true, consumer);
//
//        //不关闭资源，应该一直监听消息
//        //channel.close();
//        //connection.close();
//    }
}
