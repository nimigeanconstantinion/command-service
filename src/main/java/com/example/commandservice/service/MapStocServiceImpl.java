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
            log.info("cmdAdd: "+mp.toString());
            mapStocRepo.save(mp);
//            return mapStocRepo.findByCodProdus(mp.getIdIntern()).get();
        }catch (RuntimeException e){
            log.error("cmdUpdErr: "+mp.toString());

            throw new RuntimeException("Nu am reusit add din service impl!!");
        }

    }

    @Override
    public boolean delMapStoc(String idp) {
        Optional<MapStocOptim> mp=mapStocRepo.findByCodProdus(idp);
        if(mp.isEmpty()){
            log.error("cmdDelErrNoCode: "+mp.toString());

            throw new RuntimeException("Produsul cu cod-ul indicat nu exista!!");
        }
//        log.info(mk,mp.get().getArticol());
        log.info("cmdDel: "+mp.toString());
        mapStocRepo.delete(mp.get());
        return true;
    }

    @Override
    public MapStocOptim updMapStoc(MapStocOptim mp) {
      Optional<MapStocOptim> op=mapStocRepo.findByCodProdus(mp.getIdIntern().trim());
      if(op.isEmpty()){
          log.error("cmdUpdNoCode:"+mp.getIdIntern());
          throw new RuntimeException("Maparea cu codul dat , nu exista!!");
      }
      try{
          MapStocOptim mpp=op.get();
          mpp.setNr_zile(mp.getNr_zile());
          log.info("UPDATE!!");
//          log.info("commUpd-",op.toString());
          log.info("cmdUpd: "+mpp.toString());

          return mapStocRepo.saveAndFlush(mp);

      }catch (RuntimeException e){
          log.error("cmdUpdErr: "+mp.toString());

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
            log.info("cmdUpdBulk: "+lista.size());
            mapStocRepo.saveAllAndFlush(lista);
            return true;
        }catch (RuntimeException e){
            log.error("cmdUpdErrBulk: "+lista.size());
            throw new RuntimeException("Nu am putut salva produsele noi!");
        }
    }


}
