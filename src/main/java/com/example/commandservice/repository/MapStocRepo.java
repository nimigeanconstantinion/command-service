package com.example.commandservice.repository;

import com.example.commandservice.model.MapStocOptim;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional

public interface MapStocRepo extends JpaRepository<MapStocOptim,Long> {

    @Query(value = "select m from MapStoc m where trim(m.idIntern)=?1")
    Optional<MapStocOptim> findByCodProdus(String idA);
}
