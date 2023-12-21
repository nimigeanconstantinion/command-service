package com.example.commandservice.service;

import com.example.commandservice.model.MapStocOptim;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MapStocService {
    public MapStocOptim getByID(String idp);

    @Transactional
    public void addMapStoc(MapStocOptim mp);
    @Transactional
    public boolean delMapStoc(String idp);
    @Transactional
    public MapStocOptim updMapStoc(MapStocOptim mp);
    public List<MapStocOptim> getAllMapStoc();
    @Transactional
    public boolean saveBulk(List<MapStocOptim> lista);



}
