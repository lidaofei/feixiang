package com.feixiang.kafka.demo.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 不带回调生产者
 */
public class ProducerSimpler {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步发送
        aSyncSend();
        //异步发送，待回调方法
        aSyncSend2();
        //同步发送
        syncSend();
    }
    /**
     * 异步发送
     * //kafka 集群，broker-list
     * props.put("bootstrap.servers", "hadoop102:9092");
     * props.put("acks", "all");
     * //重试次数
     * props.put("retries", 1);
     * //批次大小
     * props.put("batch.size", 16384);
     * //等待时间
     * props.put("linger.ms", 1);
     * //RecordAccumulator 缓冲区大小
     * props.put("buffer.memory", 33554432);
     * props.put("key.serializer",
     * "org.apache.kafka.common.serialization.StringSerializer");
     * props.put("value.serializer",
     * "org.apache.kafka.common.serialization.StringSerializer");
     */
    public static void aSyncSend() {
        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092");
        props.put("acks","all");
        props.put("retries",1);
        //16kb
        props.put("batch.size",16384);
        props.put("linger.ms",1);
        //32M
        props.put("buffer.memory",33554432);
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String,String> producer = new KafkaProducer<>(props);

        String topic = "dafei-topic";
        ProducerRecord<String,String> producerRecord =
                new ProducerRecord<>(topic,"order_01","order_info_value");
        //异步发送,不带回调的发送
        producer.send(producerRecord);
    }

    /**
     * 异步发送，待回调方法
     */
    public static void aSyncSend2() {
        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092");
        props.put("acks","all");
        props.put("retries",1);
        //16kb
        props.put("batch.size",16384);
        props.put("linger.ms",1);
        //32M
        props.put("buffer.memory",33554432);
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String,String> producer = new KafkaProducer<>(props);
        String topic = "dafei-topic";
        ProducerRecord<String,String> producerRecord =
                new ProducerRecord<>(topic,"order_01","order_info_value");
        //异步发送
//        producer.send(producerRecord, new Callback() {
//            @Override
//            public void onCompletion(RecordMetadata metadata, Exception exception) {
//                //发送成功
//                if(exception == null){
//                    System.out.println("发送成功");
//                } else {
//                    //发送失败
//                    //发送失败会自动重试，不需要手动重试,会自动重试
//                    exception.printStackTrace();
//                }
//            }
//        });

        //lambda 表达式写法
        producer.send(producerRecord,(metadata,exception) -> {
            //接受到服务器的ack时调用，发送成功
            if(exception == null){
                System.out.println("发送成功");
            } else {
                //发送失败
                //发送失败会自动重试，不需要手动重试,会自动重试
                exception.printStackTrace();
            }
        });
    }

    /**
     * 同步发送
     */
    public static void syncSend() throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092");
        props.put("acks","all");
        props.put("retries",1);
        //16kb
        props.put("batch.size",16384);
        props.put("linger.ms",1);
        //32M
        props.put("buffer.memory",33554432);
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String,String> producer = new KafkaProducer<>(props);
        String topic = "dafei-topic";
        ProducerRecord<String,String> producerRecord =
                new ProducerRecord<>(topic,"order_01","order_info_value");

        //发送消息，返回future方法，不会阻塞线程
        Future<RecordMetadata> futureResult = producer.send(producerRecord);
        //获取返回值，阻塞当前线程，相当于同步发送
        futureResult.get();
    }
}
