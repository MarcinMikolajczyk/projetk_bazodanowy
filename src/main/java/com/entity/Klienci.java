package com.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Klienci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_klienta")
    private int id;

    private String imie;
    private String nazwisko;
    private String adres;
    private String pesel;
    private String nr_telefonu;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "klient", cascade = CascadeType.REMOVE)
    private List<Wynajem> wynajmy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getNr_telefonu() {
        return nr_telefonu;
    }

    public void setNr_telefonu(String nr_telefonu) {
        this.nr_telefonu = nr_telefonu;
    }

    public List<Wynajem> getWynajmy() {
        return wynajmy;
    }

    public void setWynajmy(List<Wynajem> wynajmy) {
        this.wynajmy = wynajmy;
    }
}
