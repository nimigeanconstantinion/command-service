package com.example.commandservice.service;

import com.example.commandservice.model.MapStocOptim;
import com.example.commandservice.repository.MapStocRepo;
import lombok.extern.slf4j.Slf4j;
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
            log.info("_PLUS:",mp);
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
        log.info("_MINUS:",mp.get());
        mapStocRepo.delete(mp.get());
        return true;
    }

    @Override
    public MapStocOptim updMapStoc(MapStocOptim mp) {
      Optional<MapStocOptim> op=mapStocRepo.findByCodProdus(mp.getIdIntern().trim());
      if(op.isEmpty()){
          throw new RuntimeException("Maparea cu codul dat , nu exista!!");
      }
      try{
          log.info("_UPD:",mp);
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
