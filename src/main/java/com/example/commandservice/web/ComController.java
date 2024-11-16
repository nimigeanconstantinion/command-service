package com.example.commandservice.web;

import com.example.commandservice.model.MapStocOptim;
import com.example.commandservice.rabbitMQListener.ConsumerConfig;
import com.example.commandservice.rabbitMQListener.MyMessage;
import com.example.commandservice.rabbitMQListener.MyMessageListener;
import com.example.commandservice.repository.MapStocRepo;
import com.example.commandservice.service.MapStocService;
import com.example.commandservice.service.MapStocServiceImpl;
import jakarta.persistence.PostUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/command")
//@CrossOrigin
@Slf4j
public class ComController {

    private MapStocService mapStocService;

    private MyMessageListener myMessageListener;
    public ComController( MapStocService mapStocService,MyMessageListener myMessageListener) {

        this.mapStocService = mapStocService;
        this.myMessageListener=myMessageListener;

    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add")
    public ResponseEntity<MapStocOptim> addMapArt(@RequestBody MapStocOptim mapstoc){
       try {

            mapStocService.addMapStoc(mapstoc);
            MapStocOptim amp=mapStocService.getByID(mapstoc.getIdIntern());

            return ResponseEntity.ok(amp);
       }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
       }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getallmap")
    public ResponseEntity<List<MapStocOptim>> getAllMapArt(){
//            try{
//                List<MapStocOptim> mpp=mapStocService.getAllMapStoc();
//                return ResponseEntity.ok(mpp);
//            }catch (RuntimeException e){
//                throw new RuntimeException(e.getMessage());
//            }
        return ResponseEntity.ok(mapStocService.getAllMapStoc());
    }


    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/del/{idP}")

    public ResponseEntity<Boolean> delMapArt(@PathVariable String idP){
//        MyMessage myMessage=myMessageListener.receiveMessage(MyMessage my);

                return ResponseEntity.ok(true);
//                return ResponseEntity.ok(mapStocService.delMapStoc(idP));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getbyidp/{idP}")
    public ResponseEntity<MapStocOptim> getMapArt(@PathVariable String idP) throws RuntimeException{
        try {
            return ResponseEntity.ok(mapStocService.getByID(idP));
        }catch (RuntimeException e){
            throw e;

        }


    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/update")
    public ResponseEntity<MapStocOptim> updateMapArt(@RequestBody MapStocOptim mapstoc){
        log.info("COMM_UPDATE:"+mapstoc.toString());
        log.info("Updateeeeeeee");
       return ResponseEntity.ok(mapStocService.updMapStoc(mapstoc));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/bulk")
    public ResponseEntity<Boolean> saveBulkList(@RequestBody List<MapStocOptim> lista){
        return ResponseEntity.ok(mapStocService.saveBulk(lista));
    }

}
