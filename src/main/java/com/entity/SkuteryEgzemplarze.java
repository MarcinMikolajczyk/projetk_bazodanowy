package com.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class SkuteryEgzemplarze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nr_rejestracyjny;

    @ManyToOne
    @JoinColumn(name = "skutery_id")
    private Skutery skuter;

    @OneToMany(mappedBy = "egzemplarz", fetch = FetchType.EAGER)
    private List<Wynajem> wynajemy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNr_rejestracyjny() {
        return nr_rejestracyjny;
    }

    public void setNr_rejestracyjny(String nr_rejestracyjny) {
        this.nr_rejestracyjny = nr_rejestracyjny;
    }

    public Skutery getSkuter() {
        return skuter;
    }

    public void setSkuter(Skutery skuter) {
        this.skuter = skuter;
    }

    public List<Wynajem> getWynajemy() {
        return wynajemy;
    }

    public void setWynajemy(List<Wynajem> wynajemy) {
        this.wynajemy = wynajemy;
    }
}
