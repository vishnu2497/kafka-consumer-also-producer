package com.ns.kafka.kafkaconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class KafkaConsumerApplication {

    @Autowired
    private KafkaTemplate<String, String> prodTemplate;

    private final String topic = "usertopic1";

    List<String> messages = new ArrayList<>();


    @KafkaListener(topics = "usertopic", groupId = "vishnu", containerFactory = "kafkaListenerContainerFactory")
    public void getMessageFromKafka(String data) {
        System.out.println("Consumed Message->" + data);
        prodTemplate.send(topic, "*****Message from Consumer Application from kafka*****,The Process was done");


    }


//    @KafkaListener(topics = "usertopic1", groupId = "kk", containerFactory = "objectKafkaListenerContainerFactory")
//    public void getMessageFromKafka1(String data) {
//        System.out.println("Consumed Message in another method->" + data);
//
//    }


    @GetMapping("/consume")
    public List<String> consumeMessage() {
        return messages;
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

}
