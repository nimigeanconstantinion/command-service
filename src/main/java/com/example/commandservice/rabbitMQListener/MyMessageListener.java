package com.example.commandservice.rabbitMQListener;
import com.example.commandservice.model.MapStocOptim;
import com.example.commandservice.service.MapStocServiceImpl;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MyMessageListener{

    private MapStocServiceImpl mapStocService;

    public MyMessageListener(MapStocServiceImpl mapStocService){
        this.mapStocService=mapStocService;
    }
    @RabbitListener(queues = ConsumerConfig.QUEUE_ONE)
    public void receiveMessageString(MyMessage<String> message) {

        if(message.getMessage().trim().equals("DEL_PROD_ID")){
            mapStocService.delMapStoc(message.getContent().trim());
            log.info("A mers stergerea");

            System.out.println("Received message: " +message.toString());

        }

    }

    @RabbitListener(queues = ConsumerConfig.QUEUE_TWO)
    public void receiveMessageMapStocOpt(MyMessage<MapStocOptim> message) {

        if(message.getMessage().trim().equals("UPD_PROD")){
            mapStocService.updMapStoc(message.getContent());
            log.info("A mers stergerea");

            System.out.println("Received message: " +message.toString());

        }
    }

    @RabbitListener(queues = ConsumerConfig.QUEUE_THREE)
    public void receiveMessageListMapStocOpt(MyMessage<List<MapStocOptim>> message) {

        if(message.getMessage().trim().equals("UPD_PROD_BULK")){
            mapStocService.saveBulk(message.getContent());
            log.info("A mers UPDATE BULK");

            System.out.println("Received message: " +message.toString());

        }
    }
}
