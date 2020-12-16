package com.feixiang.rocketmq.demo.base;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * 消费者
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group_ldf_test");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe("topic_ldf_test","*");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    String tags = msg.getTags();
                    System.out.println("接受到消息，tags="+tags+"消息体="+new String(msg.getBody()));
                }
                //处理成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                //处理失败时，过一会儿重新退送
                //return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                //顺序消息时，失败了，要用等一下在推送，这段时间不消费这个messagequeue的消息
                //return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
            }
        });

        consumer.start();
    }

}
