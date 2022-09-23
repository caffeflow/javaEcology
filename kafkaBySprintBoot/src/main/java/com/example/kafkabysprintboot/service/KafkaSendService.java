package com.example.kafkabysprintboot.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author xj
 * @create 2022-09-20 19:48
 **/
@Service
public class KafkaSendService {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    /**
     * 异步示例
     * */
    public void sendAsync(String topic, Integer partition, String key, @Nullable Object data){
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, partition, key, data);
        // 增加回调函数,异步处理
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                RecordMetadata recordMetadata = result.getRecordMetadata();
                System.out.println("异步发送消息成功" + recordMetadata.topic() + "\t" + recordMetadata.partition() + "\t" + recordMetadata.offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("发送消息失败："+ ex.getMessage());
            }
        });
    }

    /**
     * 同步示例
     * */
    public void sendSync(String topic, Integer partition, String key, @Nullable Object data){
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic, partition, key, data);
        try {
            SendResult<String, Object> result = kafkaTemplate.send(producerRecord).get(10, TimeUnit.SECONDS);
            RecordMetadata recordMetadata = result.getRecordMetadata();
            System.out.println("异步发送消息成功" + recordMetadata.topic() + "\t" + recordMetadata.partition() + "\t" + recordMetadata.offset());
        }
        catch (ExecutionException e) {
            System.out.println("发送消息失败："+ e.getMessage());
        }
        catch (TimeoutException | InterruptedException e) {
            System.out.println("发送消息失败："+ e.getMessage());
        }
    }
}
