package com.ns.kafka.kafkaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class KafkaConsumerApplication {

    List<String> messages = new ArrayList<>();


    @KafkaListener(topics = "usertopic",groupId = "vishnu", containerFactory = "kafkaListenerContainerFactory")
    public void getMessageFromKafka(String data) {
        System.out.println("Consumed Message->"+data);
    }

    @GetMapping("/consume")
    public List<String> consumeMessage(){
        return messages;
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

}
