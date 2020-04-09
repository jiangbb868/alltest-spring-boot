package com.alkaid.alltest.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class KafkaProducer {
    private static final String MY_TOPIC = "TOPIC_LIN_LIANG";

    @Autowired
    KafkaTemplate kafkaTemplate;

    public KafkaProducer() {

    }

    public void produce(){
        Message message = new Message();
        message.setId(12L);
        message.setMsg("hello jack");
        message.setDate(new Date());
        kafkaTemplate.send(MY_TOPIC,message);
    }

}
