package com.web.controller.admin.scooterclass;

import com.data.JpaScooter;
import com.entity.Cennik;
import org.postgresql.util.PSQLException;
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
@RequestMapping("/admin/class")
public class ClassController {

    @Autowired
    private JpaScooter jpaScooter;

    @RequestMapping
    public String _class(Model model){

        TypedQuery<Cennik> query = jpaScooter.getEn().createQuery("select s from Cennik s", Cennik.class);
        List<Cennik> cennniki = query.getResultList();;

        model.addAttribute("cenniki", cennniki);

        return "admin/class/class";
    }

    @RequestMapping(value = "/{id}")
    public String deleteClass(@PathVariable int id){

            jpaScooter.deleteCennik(id);

        return "redirect:/admin/class";
    }

    @RequestMapping(value = "/add")
    public String add(Model model){

        Cennik cennik = new Cennik();
        model.addAttribute("cennik", cennik);

        return "admin/class/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add_save(@ModelAttribute Cennik cennik){
        jpaScooter.updateCennik(cennik);

        return "redirect:/admin/class";
    }
}
