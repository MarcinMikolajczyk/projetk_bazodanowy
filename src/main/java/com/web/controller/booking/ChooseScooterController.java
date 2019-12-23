package com.web.controller.booking;

import com.data.JpaClient;
import com.data.JpaScooter;
import com.entity.Klienci;
import com.entity.Skutery;
import com.entity.SkuteryEgzemplarze;
import com.entity.Wynajem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.TypedQuery;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/booking/choose")
public class ChooseScooterController {

    @Autowired
    private JpaScooter jpaScooter;
    @Autowired
    private JpaClient jpaClient;

    private ArrayList<Object> copy_wynajem_klient = new ArrayList<Object>();
    private List<SkuteryEgzemplarze> egz;

    @RequestMapping
    public String choose(@ModelAttribute Wynajem wynajem,
                         @RequestParam String imie,
                         @RequestParam String nazwisko,
                         @RequestParam String adres,
                         @RequestParam String pesel,
                         @RequestParam String nr_telefonu,
                         Model model) throws ParseException {

        TypedQuery<SkuteryEgzemplarze> query = jpaScooter
                .getEn().createQuery("select e from SkuteryEgzemplarze e", SkuteryEgzemplarze.class);

        List<SkuteryEgzemplarze> all = query.getResultList();
        egz = new ArrayList<SkuteryEgzemplarze>();

        for(int i = 0; i < all.size(); i++){
            if (all.get(i).getWynajemy().size() == 0){
                egz.add(all.get(i));
            }else{
                boolean find = false;
                for(int j = 0; j < all.get(i).getWynajemy().size(); j++){

                    for(Date w = all.get(i).getWynajemy().get(j).getData_wyporzyczenia();
                       w.before(addDays(all.get(i).getWynajemy().get(j).getData_oddania(), 1));  w = addDays(w, 1)){

                      //  System.out.println("increments w: " + w);
                       if(w.equals(wynajem.getData_wyporzyczenia())  == true|| w.equals(wynajem.getData_oddania()) == true){
                           find = true;
                           break;
                       }
                   }

                }
                if(find == false)
                     egz.add(all.get(i));

            }
        }

        if(egz.size() == 0){
            return "booking/emptylist";
        }

        Klienci k = new Klienci();
        k.setImie(imie);
        k.setNazwisko(nazwisko);
        k.setAdres(adres);
        k.setPesel(pesel);
        k.setNr_telefonu(nr_telefonu);

        Klienci _k = jpaClient.findKlient(k.getPesel());

        if(_k != null){
            k = _k;
        }

        copy_wynajem_klient.add(wynajem);
        copy_wynajem_klient.add(k);

        SelectedScooters selected = new SelectedScooters();

        model.addAttribute("egz", egz);
        model.addAttribute("selected", selected);

        return "booking/choose";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String choose_post(@ModelAttribute SelectedScooters selected){

        Wynajem wynajem = (Wynajem) copy_wynajem_klient.get(0);
        Klienci klienci = (Klienci) copy_wynajem_klient.get(1);
        List<SkuteryEgzemplarze> wybrane = new ArrayList<SkuteryEgzemplarze>();

        for(int i = 0; i < selected.getSelected().size(); i++){
            wybrane.add(jpaScooter.findEgz(Integer.parseInt(selected.getSelected().get(i))));
        }

        jpaClient.book(klienci,wynajem,wybrane);

        return "redirect:/";
    }

    public Date addDays(Date date, int days) throws ParseException {

        String dt = ""+date;  // Start date
        System.out.println(dt);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        c.add(Calendar.DATE, days);  // number of days to add
        dt = sdf.format(c.getTime());
        System.out.println(dt);
        long t = c.getTimeInMillis();

        return new java.sql.Date(t);
    }

}
