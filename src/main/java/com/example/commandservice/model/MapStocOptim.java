package com.example.commandservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "MapStoc")
@Table(name = "map_stoc_optim")

public class MapStocOptim implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "map_art_generator")
    @SequenceGenerator(name = "map_art_generator",initialValue = 1,allocationSize = 1)
    private long id;

    @Column(name="id_intern")
    private String idIntern;

    @Column(name = "articol")
    private String articol;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "grupa")
    private String grupa;

    @Column(name = "idFurn")
    private int id_furn;

    @Column(name = "furniz")
    private String furniz;

    @Column(name = "nrZile")
    private int nr_zile;

    public String toString(){
        return "id:"+id+";articol:"+articol.trim()+";id_furniz:"+id_furn+";furnizor:"+furniz.trim()+";nr_zile:"+nr_zile;
    }
}
