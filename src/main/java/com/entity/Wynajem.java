package com.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Wynajem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_wynajmu;

    @ManyToOne
    @JoinColumn(name = "klient_id")
    private Klienci klient;

    @ManyToOne
    @JoinColumn(name = "egzemplarz_id")
    private SkuteryEgzemplarze egzemplarz;

    private Date data_wyporzyczenia;
    private Date data_oddania;

    public int getId_wynajmu() {
        return id_wynajmu;
    }

    public void setId_wynajmu(int id_wynajmu) {
        this.id_wynajmu = id_wynajmu;
    }

    public Klienci getKlient() {
        return klient;
    }

    public void setKlient(Klienci klient) {
        this.klient = klient;
    }

    public SkuteryEgzemplarze getEgzemplarz() {
        return egzemplarz;
    }

    public void setEgzemplarz(SkuteryEgzemplarze egzemplarz) {
        this.egzemplarz = egzemplarz;
    }

    public Date getData_wyporzyczenia() {
        return data_wyporzyczenia;
    }

    public void setData_wyporzyczenia(Date data_wyporzyczenia) {
        this.data_wyporzyczenia = data_wyporzyczenia;
    }

    public Date getData_oddania() {
        return data_oddania;
    }

    public void setData_oddania(Date data_oddania) {
        this.data_oddania = data_oddania;
    }
}
