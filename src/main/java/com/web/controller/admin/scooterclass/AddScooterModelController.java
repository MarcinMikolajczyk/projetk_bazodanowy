package com.web.controller.admin.scooterclass;

import com.data.JpaScooter;
import com.entity.Cennik;
import com.entity.Skutery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.TypedQuery;
import java.util.List;

@Controller
@RequestMapping("/admin/scooter/model/add")
public class AddScooterModelController {

    @Autowired
    private JpaScooter jpaScooter;


    @RequestMapping
    public String addScooter(Model model){

        TypedQuery<Cennik> query = jpaScooter.getEn()
                .createQuery("select c from Cennik c", Cennik.class);
        List<Cennik> cenniki = query.getResultList();
        Skutery skuter = new Skutery();
        Cennik cennik = new Cennik();

        model.addAttribute("skuter", skuter);
        model.addAttribute("cenniki", cenniki);
        model.addAttribute("cennik", cennik);

        return "admin/scooters/addModel";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String saveScooter(@ModelAttribute Skutery skuter,
                              @ModelAttribute Cennik cennik,
                              BindingResult result){

        cennik = jpaScooter.findCennik(cennik.getId());
        jpaScooter.addSkutery(skuter, cennik);

        return "redirect:/admin/scooter";
    }

}
