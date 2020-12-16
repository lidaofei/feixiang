package com.feixiang.rocketmq.demo.base;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * 异步发送消息
 */
public class AsyncProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group_ldf_test");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for(int i=0 ; i<10 ; i++){
            Message message = new Message("topic_ldf_test", "tag_ldf", "hello ldf".getBytes());

            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送成功，"+ JSONObject.toJSONString(sendResult));
                }

                @Override
                public void onException(Throwable throwable) {
                    throwable.printStackTrace();
                    System.out.println("发送失败，"+throwable);
                }
            });
            //睡眠1秒
            TimeUnit.SECONDS.sleep(1);
        }

        producer.shutdown();
    }
}
