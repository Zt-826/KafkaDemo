package com.example.producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class ProducerController {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;


//    @Transactional
    @RequestMapping("/send")
    public String sendMessage(@RequestParam("key") String key, @RequestParam("value") String value) {
        try {
            for (int i = 0; i < 3; i++) {
                ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test_topic", 0, key + i
                        , value + i);
//                future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
//                    @Override
//                    public void onFailure(Throwable ex) {
//                        log.error("Failed to send message caused by {}", ex.getMessage());
//                    }
//
//                    @Override
//                    public void onSuccess(SendResult<String, Object> result) {
//                        log.info("Succeed to send message {}", result.toString());
//                    }
//                });
            }
//        kafkaTemplate.executeInTransaction((KafkaOperations.OperationsCallback) kafkaOperations -> {
//            kafkaOperations.send("test_topic", 0, key, value);
//            throw new RuntimeException("fail");
//        });
            return "发送成功";
        } catch (Exception e) {
            log.error("Failed to send message caused by {}", e.getMessage());
            return "发送失败";
        }
    }
}
