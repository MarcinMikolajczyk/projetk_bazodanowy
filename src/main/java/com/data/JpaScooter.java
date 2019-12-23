package com.data;

import com.entity.Cennik;
import com.entity.Skutery;
import com.entity.SkuteryEgzemplarze;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class JpaScooter {

    @PersistenceContext
    EntityManager en;

    public Cennik findCennik(int id){
        return en.find(Cennik.class, id);
    }
    public SkuteryEgzemplarze findEgz(int id){
        return en.find(SkuteryEgzemplarze.class, id);
    }
    public Skutery findSkuter(int id){
        return en.find(Skutery.class, id);
    }

    public Cennik updateCennik(Cennik cennik){
        return en.merge(cennik);
    }

    public void addSkutery(Skutery skuter, Cennik cennik){

        Query q = en.createNativeQuery("select nextval('skutery_id_seq')");
        BigInteger id = (BigInteger) q.getSingleResult();

        en.createNativeQuery("insert into skutery (id, marka, model, rocznik, cennik_id) " +
                "values (?, ?, ?, ?, ?)")
                .setParameter(1, id)
                .setParameter(2, skuter.getMarka())
                .setParameter(3, skuter.getModel())
                .setParameter(4, skuter.getRocznik())
                .setParameter(5, cennik.getId()).executeUpdate();

    }

    public void addEgzemplarz(SkuteryEgzemplarze egz, Skutery skuter){

        Query q = en.createNativeQuery("select nextval('skuteryegzemplarze_id_seq')");
        BigInteger id = (BigInteger) q.getSingleResult();

        en.createNativeQuery("insert into skuteryegzemplarze (id, nr_rejestracyjny, skutery_id) " +
                "values (?, ?, ?)")
                .setParameter(1, id)
                .setParameter(2, egz.getNr_rejestracyjny())
                .setParameter(3, skuter.getId()).executeUpdate();

    }

    public void deleteCennik(int id){
        Cennik c = en.find(Cennik.class, id);
        en.remove(c);
    }

    public void deleteSkuter(int id){
        Skutery c = en.find(Skutery.class, id);
        en.remove(c);
    }

    public void deleteEgzemplarz(int id){
        SkuteryEgzemplarze c = en.find(SkuteryEgzemplarze.class, id);
        en.remove(c);
    }


    public EntityManager getEn(){ return en; }

}
