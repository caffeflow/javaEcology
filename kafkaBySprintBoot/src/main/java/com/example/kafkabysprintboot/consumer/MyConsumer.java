package com.example.kafkabysprintboot.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xj
 * @create 2022-09-20 19:18
 **/
@Component
public class MyConsumer {
    @KafkaListener(topics = {"topic-async","topic-sync"})
    public void onMessage(ConsumerRecord<String,String> record){
        System.out.println("消费消息：" + "\t"
            + record.topic() + "\t"
                + record.partition() + "\t"
                + record.offset() + "\t"
                +record.key() + "\t"
                + record.value());
    }
}
