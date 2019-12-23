package com.data;

import com.entity.Klienci;
import com.entity.Skutery;
import com.entity.SkuteryEgzemplarze;
import com.entity.Wynajem;
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
public class JpaClient {

    @PersistenceContext
    private EntityManager en;

    public void book(Klienci klienci, Wynajem wynajem, List<SkuteryEgzemplarze> wybrane){

        klienci = en.merge(klienci);

        for(int i = 0; i < wybrane.size(); i++){
            Query q = en.createNativeQuery("select nextval('wynajem_id_wynajmu_seq')");
            BigInteger id_wynajmu = (BigInteger) q.getSingleResult();
            en.createNativeQuery("insert into wynajem (id_wynajmu, data_oddania, data_wyporzyczenia, egzemplarz_id, klient_id) values (?, ?, ?, ?, ?)")
                    .setParameter(1, id_wynajmu)
                    .setParameter(2, wynajem.getData_oddania())
                    .setParameter(3, wynajem.getData_wyporzyczenia())
                    .setParameter(4, wybrane.get(i).getId())
                    .setParameter(5, klienci.getId()).executeUpdate();
        }
    }

    public Klienci findKlient(String pesel){
        TypedQuery<Klienci> query = getEn().createQuery("select k from Klienci k  where k.pesel = :pesel", Klienci.class).setParameter("pesel", pesel);
        return query.getSingleResult();
    }

    public void deleteKlient(int id){
        Klienci k = en.find(Klienci.class, id);
        en.remove(k);
    }

    public void deleteWynajem(int id){
        Wynajem w = en.find(Wynajem.class, id);
        en.remove(w);
    }

    public EntityManager getEn(){ return en; }

}
