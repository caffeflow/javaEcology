package com.example.kafkabysprintboot.controller;

import com.example.kafkabysprintboot.service.KafkaSendService;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xj
 * @create 2022-09-20 19:11
 **/
@RestController
public class kafkaProducerController {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
    @Autowired
    private KafkaSendService kafkaSendService;

    // 异步消息
    @RequestMapping("/send/async/{message}")
    public boolean sendAsync(@PathVariable String message){
        kafkaSendService.sendAsync("topic-async",0,"key0",message);
        return true;
    }

    // 同步消息
    @RequestMapping("/send/sync/{message}")
    public boolean sendSync(@PathVariable String message){
        kafkaSendService.sendSync("topic-sync",0,"key0",message);
        return true;
    }

    // 事务消息
    @RequestMapping("/send/transaction/{message}")
    public boolean sendTransaction(@PathVariable String message){
        Boolean bool = kafkaTemplate.executeInTransaction(kafkaTemplate -> {
            kafkaTemplate.send("Topic-transaction", "message-transaction");
            return true;
        });
        return bool;
    }
}
