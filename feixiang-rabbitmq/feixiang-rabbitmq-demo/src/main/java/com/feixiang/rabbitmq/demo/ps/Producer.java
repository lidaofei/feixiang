package com.feixiang.rabbitmq.demo.ps;

import com.feixiang.rabbitmq.demo.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lidaofei
 * @date 2020/11/26 7:13
 */
public class Producer {

    /**
     * 发布订阅模式，使用交换机类型为：fanout
     * 一个生产者发消息到交换机上，交换机分别绑定了两个队列
     * 消费者分别消费一个队列，达到广播的效果
     * @param args
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        String fanout_exchange = "fanout_exchange";
        channel.exchangeDeclare(fanout_exchange, BuiltinExchangeType.FANOUT);

        String fanout_queue1 = "fanout_queue1";
        String fanout_queue2 = "fanout_queue2";
        channel.queueDeclare(fanout_queue1,true,false,false,null);
        channel.queueDeclare(fanout_queue2,true,false,false,null);

        channel.queueBind(fanout_queue1,fanout_exchange,"");
        channel.queueBind(fanout_queue2,fanout_exchange,"");

        for(int i=0; i<10; i++){
            String message = "hello ldf,发布订阅模式";

            channel.basicPublish(fanout_exchange,"",null,message.getBytes());
        }

        channel.close();
        connection.close();
    }

    //参考
//    //交换机名称
//    static final String FANOUT_EXCHAGE = "fanout_exchange";
//    //队列名称
//    static final String FANOUT_QUEUE_1 = "fanout_queue_1";
//    //队列名称
//    static final String FANOUT_QUEUE_2 = "fanout_queue_2";
//
//    public static void main(String[] args) throws Exception {
//        //创建连接
//        Connection connection = ConnectionUtil.getConnection();
//        // 创建频道
//        Channel channel = connection.createChannel();
//        /**
//         * 声明交换机
//         * 参数1：交换机名称
//         * 参数2：交换机类型，fanout、topic、direct、headers
//         */
//        channel.exchangeDeclare(FANOUT_EXCHAGE, BuiltinExchangeType.FANOUT);
//
//        // 声明（创建）队列
//        /**
//         * 参数1：队列名称
//         * 参数2：是否定义持久化队列
//         * 参数3：是否独占本次连接
//         * 参数4：是否在不使用的时候自动删除队列
//         * 参数5：队列其它参数
//         */
//        channel.queueDeclare(FANOUT_QUEUE_1, true, false, false, null);
//        channel.queueDeclare(FANOUT_QUEUE_2, true, false, false, null);
//
//        //队列绑定交换机
//        channel.queueBind(FANOUT_QUEUE_1, FANOUT_EXCHAGE, "");
//        channel.queueBind(FANOUT_QUEUE_2, FANOUT_EXCHAGE, "");
//
//        for (int i = 1; i <= 10; i++) {
//            // 发送信息
//            String message = "你好；小兔子！发布订阅模式--" + i;
//            /**
//             * 参数1：交换机名称，如果没有指定则使用默认Default Exchage
//             * 参数2：路由key,简单模式可以传递队列名称
//             * 参数3：消息其它属性
//             * 参数4：消息内容
//             */
//            channel.basicPublish(FANOUT_EXCHAGE, "", null, message.getBytes());
//            System.out.println("已发送消息：" + message);
//        }
//
//        // 关闭资源
//        channel.close();
//        connection.close();
//    }
}
