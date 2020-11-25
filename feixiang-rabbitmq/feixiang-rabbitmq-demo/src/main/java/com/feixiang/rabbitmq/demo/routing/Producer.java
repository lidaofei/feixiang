package com.feixiang.rabbitmq.demo.routing;

import com.feixiang.rabbitmq.demo.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lidaofei
 * @date 2020/11/26 7:35
 */
public class Producer {

    /**
     * 路由模式，交换机类型为:direct
     * 必须要指定路由key
     * @param args
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        String direct_exchange = "direct_exchange";
        channel.exchangeDeclare(direct_exchange, BuiltinExchangeType.FANOUT);

        String direct_queue1 = "direct_queue1";
        String direct_queue2 = "direct_queue2";
        channel.queueDeclare(direct_queue1,true,false,false,null);
        channel.queueDeclare(direct_queue2,true,false,false,null);

        channel.queueBind(direct_queue1,direct_exchange,"insert");
        channel.queueBind(direct_queue2,direct_exchange,"update");

        for(int i=0; i<10; i++){
            String message = "hello ldf,路由模式";

            //只会发送到路由key为update的队列中
            channel.basicPublish(direct_exchange,"update",null,message.getBytes());
        }

        channel.close();
        connection.close();
    }

    //参考
//    //交换机名称
//    static final String DIRECT_EXCHAGE = "direct_exchange";
//    //队列名称
//    static final String DIRECT_QUEUE_INSERT = "direct_queue_insert";
//    //队列名称
//    static final String DIRECT_QUEUE_UPDATE = "direct_queue_update";
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
//        channel.exchangeDeclare(DIRECT_EXCHAGE, BuiltinExchangeType.DIRECT);
//
//        // 声明（创建）队列
//        /**
//         * 参数1：队列名称
//         * 参数2：是否定义持久化队列
//         * 参数3：是否独占本次连接
//         * 参数4：是否在不使用的时候自动删除队列
//         * 参数5：队列其它参数
//         */
//        channel.queueDeclare(DIRECT_QUEUE_INSERT, true, false, false, null);
//        channel.queueDeclare(DIRECT_QUEUE_UPDATE, true, false, false, null);
//
//        //队列绑定交换机
//        channel.queueBind(DIRECT_QUEUE_INSERT, DIRECT_EXCHAGE, "insert");
//        channel.queueBind(DIRECT_QUEUE_UPDATE, DIRECT_EXCHAGE, "update");
//
//        // 发送信息
//        String message = "新增了商品。路由模式；routing key 为 insert " ;
//        /**
//         * 参数1：交换机名称，如果没有指定则使用默认Default Exchage
//         * 参数2：路由key,简单模式可以传递队列名称
//         * 参数3：消息其它属性
//         * 参数4：消息内容
//         */
//        channel.basicPublish(DIRECT_EXCHAGE, "insert", null, message.getBytes());
//        System.out.println("已发送消息：" + message);
//
//        // 发送信息
//        message = "修改了商品。路由模式；routing key 为 update" ;
//        /**
//         * 参数1：交换机名称，如果没有指定则使用默认Default Exchage
//         * 参数2：路由key,简单模式可以传递队列名称
//         * 参数3：消息其它属性
//         * 参数4：消息内容
//         */
//        channel.basicPublish(DIRECT_EXCHAGE, "update", null, message.getBytes());
//        System.out.println("已发送消息：" + message);
//
//        // 关闭资源
//        channel.close();
//        connection.close();
//    }
}
