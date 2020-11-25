package com.feixiang.rabbitmq.demo.routing;

import com.feixiang.rabbitmq.demo.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lidaofei
 * @date 2020/11/26 7:42
 */
public class Consumer1 {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        String direct_exchange = "direct_exchange";
        channel.exchangeDeclare(direct_exchange, BuiltinExchangeType.DIRECT);

        String direct_queue1 = "direct_queue1";
        String direct_queue2 = "direct_queue2";
        channel.queueDeclare(direct_queue1,true,false,false,null);

        channel.queueBind(direct_queue1,direct_exchange,"insert");

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
        channel.basicConsume(direct_queue1,true,consumer);
    }

    //参考
//    public static void main(String[] args) throws Exception {
//        //获取连接
//        Connection connection = ConnectionUtil.getConnection();
//        // 创建频道
//        Channel channel = connection.createChannel();
//        //声明交换机
//        channel.exchangeDeclare(Producer.DIRECT_EXCHAGE, BuiltinExchangeType.DIRECT);
//
//        // 声明（创建）队列
//        /**
//         * 参数1：队列名称
//         * 参数2：是否定义持久化队列
//         * 参数3：是否独占本次连接
//         * 参数4：是否在不使用的时候自动删除队列
//         * 参数5：队列其它参数
//         */
//        channel.queueDeclare(Producer.DIRECT_QUEUE_INSERT, true, false, false, null);
//
//        //队列绑定交换机
//        channel.queueBind(Producer.DIRECT_QUEUE_INSERT, Producer.DIRECT_EXCHAGE, "insert");
//
//        //创建消费者；并设置消息处理
//        DefaultConsumer consumer = new DefaultConsumer(channel){
//            @Override
//            /**
//             * consumerTag 消息者标签，在channel.basicConsume时候可以指定
//             * envelope 消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志(收到消息失败后是否需要重新发送)
//             * properties 属性信息
//             * body 消息
//             */
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                //路由key
//                System.out.println("路由key为：" + envelope.getRoutingKey());
//                //交换机
//                System.out.println("交换机为：" + envelope.getExchange());
//                //消息id
//                System.out.println("消息id为：" + envelope.getDeliveryTag());
//                //收到的消息
//                System.out.println("消费者1-接收到的消息为：" + new String(body, "utf-8"));
//            }
//        };
//        //监听消息
//        /**
//         * 参数1：队列名称
//         * 参数2：是否自动确认，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置为false则需要手动确认
//         * 参数3：消息接收到后回调
//         */
//        channel.basicConsume(Producer.DIRECT_QUEUE_INSERT, true, consumer);
//    }
}
