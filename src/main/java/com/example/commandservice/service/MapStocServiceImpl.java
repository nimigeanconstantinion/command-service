package com.example.commandservice.service;

import com.example.commandservice.model.MapStocOptim;
import com.example.commandservice.repository.MapStocRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class MapStocServiceImpl implements MapStocService{
    private MapStocRepo mapStocRepo;

    public MapStocServiceImpl(MapStocRepo mapStocRepo) {
        this.mapStocRepo = mapStocRepo;
    }


    public MapStocOptim getByID(String ida){
        Optional<MapStocOptim> om=mapStocRepo.findByCodProdus(ida);
        if(om.isPresent()){
            return om.get();
        }
        throw new RuntimeException("Eroare din Service MapStocService");
    }

    @Override
    public void addMapStoc(MapStocOptim mp) {

        try{
            log.info("COMM_ADD:"+mp.toString());
            mapStocRepo.save(mp);
//            return mapStocRepo.findByCodProdus(mp.getIdIntern()).get();
        }catch (RuntimeException e){
            throw new RuntimeException("Nu am reusit add din service impl!!");
        }

    }

    @Override
    public boolean delMapStoc(String idp) {
        Optional<MapStocOptim> mp=mapStocRepo.findByCodProdus(idp);
        if(mp.isEmpty()){
            throw new RuntimeException("Produsul cu cod-ul indicat nu exista!!");
        }
        MDC.put("commDel",mp.get().toString());
        log.info("STERGERE PRODUS");
        Marker mk= MarkerFactory.getMarker("delProd");
//        log.info(mk,mp.get().getArticol());
        log.info("commDel: "+mp.toString());
        mapStocRepo.delete(mp.get());
        return true;
    }

    @Override
    public MapStocOptim updMapStoc(MapStocOptim mp) {
      Optional<MapStocOptim> op=mapStocRepo.findByCodProdus(mp.getIdIntern());
      if(op.isEmpty()){
          throw new RuntimeException("Maparea cu codul dat , nu exista!!");
      }
      try{
          MapStocOptim mpp=op.get();
          mpp.setNr_zile(mp.getNr_zile());
          log.info("UPDATE!!");
//          log.info("commUpd-",op.toString());
          log.info("commUpd: "+mpp.toString());

//          MDC.put("commUpd:",mp.toString());

          return mapStocRepo.saveAndFlush(mp);

      }catch (RuntimeException e){
        throw new RuntimeException("Nu am reusit UPdate!!");
      }
    }

    @Override
    public List<MapStocOptim> getAllMapStoc() {
        try {
            return mapStocRepo.findAll();
        }catch (RuntimeException e){
            throw new RuntimeException("Nu a putut lua lista  de produse!");
        }

    }

    @Override
    public boolean saveBulk(List<MapStocOptim> lista) {
        try {
            mapStocRepo.saveAllAndFlush(lista);
            return true;
        }catch (RuntimeException e){
            throw new RuntimeException("Nu am putut salva produsele noi!");
        }
    }


}
