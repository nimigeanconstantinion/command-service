package com.example.commandservice.rabbitMQListener;
import com.example.commandservice.model.MapStocOptim;
import com.example.commandservice.service.MapStocServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyMessageListener {

    private MapStocServiceImpl mapStocService;

    public MyMessageListener(MapStocServiceImpl mapStocService){
        this.mapStocService=mapStocService;
    }
    @RabbitListener(queues = ConsumerConfig.QUEUE_NAME)
    public void receiveMessage(MyMessage message) {

        if(message.getMessage().trim().equals("DEL_PROD_ID")){
            mapStocService.delMapStoc(message.getContent().toString().trim());
            log.info("A mers stergerea");
            System.out.println("Received message: " +message.toString());

        }
//        MapStocOptim mpp=(MapStocOptim) message.getContent();

    }

}
