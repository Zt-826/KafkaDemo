package com.example.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerListener {
    @KafkaListener(topics = {"test_topic"})
    public void consumer(String record, Acknowledgment ack) {
        String threadName = Thread.currentThread().getName();
        try {
            log.info("线程: {} 接收到消息: {}", threadName, record);
        } finally {
            log.info("线程: {} 异步提交", threadName);
            ack.acknowledge();
        }
    }
}
