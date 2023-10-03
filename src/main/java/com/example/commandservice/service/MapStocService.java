package com.example.commandservice.service;

import com.example.commandservice.model.MapStocOptim;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MapStocService {
    public MapStocOptim getByID(String idp);
    public void addMapStoc(MapStocOptim mp);
    public boolean delMapStoc(String idp);
    public MapStocOptim updMapStoc(MapStocOptim mp);
    public List<MapStocOptim> getAllMapStoc();
    public boolean saveBulk(List<MapStocOptim> lista);



}
