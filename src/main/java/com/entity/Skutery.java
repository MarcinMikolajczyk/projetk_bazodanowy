package com.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Skutery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String marka;
    private String model;
    private int rocznik;

    @ManyToOne
    @JoinColumn(name = "cennik_id")
    private Cennik cennik;

    @OneToMany(mappedBy = "skuter", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<SkuteryEgzemplarze> egzemplarze;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRocznik() {
        return rocznik;
    }

    public void setRocznik(int rocznik) {
        this.rocznik = rocznik;
    }

    public Cennik getCennik() {
        return cennik;
    }

    public void setCennik(Cennik cennik) {
        this.cennik = cennik;
    }

    public List<SkuteryEgzemplarze> getEgzemplarze() {
        return egzemplarze;
    }

    public void setEgzemplarze(List<SkuteryEgzemplarze> egzemplarze) {
        this.egzemplarze = egzemplarze;
    }


}
