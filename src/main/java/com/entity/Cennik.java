package com.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cennik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int cena_doba;
    private String opis;

    @OneToMany(mappedBy = "cennik", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Skutery> skutery;

    public int getId() {
        return id;
    }

    public int getCena_doba() {
        return cena_doba;
    }

    public String getOpis() {
        return opis;
    }

    public List<Skutery> getSkutery() {
        return skutery;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCena_doba(int cena_doba) {
        this.cena_doba = cena_doba;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setSkutery(List<Skutery> skutery) {
        this.skutery = skutery;
    }
}
