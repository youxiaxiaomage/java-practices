package com.yxxmg.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/9
 */
@Service
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void produce(String msg) {
        this.kafkaTemplate.send("test", msg);
    }
}
