package com.feixiang.kafka.demo.comsumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * 消费者
 */
public class ComsumerSimpler {

    /**
     * 自动提交offset
     * @param args
     */
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost");
        properties.put("group.id","ldf_test");
        properties.put("enable.auto.commit","true");
        properties.put("auto.commit.interval.ms","1000");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("dafei-topic"));
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset=%d,key=%s,value=%s%n",record.offset(),record.key(),record.value());
            }
        }
    }

    /**
     * 手动同步提交offset
     */
    public static void main2(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost");
        properties.put("group.id","ldf_test");
        properties.put("enable.auto.commit","false");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("dafei-topic"));
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset=%d,key=%s,value=%s%n",record.offset(),record.key(),record.value());
            }
            //同步提交offset
            consumer.commitSync();
        }
    }

    /**
     * 手动异步提交offset
     */
    public static void main3(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost");
        properties.put("group.id","ldf_test");
        properties.put("enable.auto.commit","false");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("dafei-topic"));
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset=%d,key=%s,value=%s%n",record.offset(),record.key(),record.value());
            }
            //异步提交offset
            consumer.commitAsync(new OffsetCommitCallback() {

                @Override
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                    if(exception != null){
                        System.out.println("提交失败了");
                    }
                }
            });
        }
    }
}
