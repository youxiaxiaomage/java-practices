package com.yxxmg.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/9
 */
@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "test")
    public void onMessage(String msg, Acknowledgment acknowledgment) {
        log.info("consume msg {}", msg);
        acknowledgment.acknowledge();
    }
}
