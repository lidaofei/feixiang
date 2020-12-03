package com.feixiang.rabbitmq.demo.work;

import com.feixiang.rabbitmq.demo.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {

    /**
     * work-queue模式
     * 一个生产者多个消费者，代码和简单生产者和消费者一样
     * 多个消费者共同消费一个主题，相互不会重复消费
     *
     */
    static final String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception{
        //创建连接
        Connection connection = ConnectionUtils.getConnection();
        /* 创建频道 */
        Channel channel = connection.createChannel();
        //声明（创建）队列
        /*
        参数一：队列名称
        参数二：是否定义持久化队列
        参数三：是否独占本次连接
        参数四：是否在不使用的时候自动删除队列
        参数五：队列其他参数
         */
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //要发送的消息
        for(int i=1;i<=30;i++){
            String message = "纯情小鸭鸭来了，你在哪里======"+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("已发送消息："+message);
        }

        //释放资源
        channel.close();
        connection.close();
    }

}
