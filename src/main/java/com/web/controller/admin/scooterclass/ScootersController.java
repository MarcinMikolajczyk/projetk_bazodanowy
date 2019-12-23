package com.web.controller.admin.scooterclass;

import com.data.JpaScooter;
import com.entity.Cennik;
import com.entity.Skutery;
import com.entity.SkuteryEgzemplarze;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.TypedQuery;
import java.util.List;

@Controller
@RequestMapping("/admin/scooter")
public class ScootersController {

    @Autowired
    private JpaScooter jpaScooter;

    @RequestMapping
    public String scooters(Model model){

        TypedQuery<Skutery> s_query = jpaScooter.getEn().createQuery("select s from Skutery s", Skutery.class);
        List<Skutery> skutery = s_query.getResultList();

        model.addAttribute("skutery", skutery);

        TypedQuery<SkuteryEgzemplarze> e_query = jpaScooter.getEn().createQuery("select s from SkuteryEgzemplarze s", SkuteryEgzemplarze.class);
        List<SkuteryEgzemplarze> egzemplarze = e_query.getResultList();

        model.addAttribute("egzemplarze", egzemplarze);

        return "/admin/scooters/scooter";
    }

    /*Usuniecie modelu s*/
    @RequestMapping(value = "/model/{id}")
    public String deleteScooter(@PathVariable int id){

        jpaScooter.deleteSkuter(id);

        return "redirect:/admin/scooter";
    }


    /*Usuniecie egzemplarza s*/
    @RequestMapping(value = "/vehicle/{id}")
    public String deleteEgz(@PathVariable int id){

        jpaScooter.deleteEgzemplarz(id);

        return "redirect:/admin/scooter";
    }


}
