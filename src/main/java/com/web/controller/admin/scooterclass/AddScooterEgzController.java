package com.web.controller.admin.scooterclass;

import com.data.JpaScooter;
import com.entity.Cennik;
import com.entity.Skutery;
import com.entity.SkuteryEgzemplarze;
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
@RequestMapping("/admin/scooter/vehicle/add")
public class AddScooterEgzController {

    @Autowired
    private JpaScooter jpaScooter;



    @RequestMapping
    public String addEgz(Model model){

        TypedQuery<Skutery> query = jpaScooter.getEn()
                .createQuery("select c from Skutery c", Skutery.class);
        List<Skutery> modele = query.getResultList();
        SkuteryEgzemplarze egz = new SkuteryEgzemplarze();
        Skutery _model = new Skutery();

        model.addAttribute("egz", egz);
        model.addAttribute("modele", modele);
        model.addAttribute("model", _model);

        return "admin/scooters/addEgz";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveScooter(@ModelAttribute SkuteryEgzemplarze egz,
                              @ModelAttribute Skutery model,
                              BindingResult result){

        System.out.println("Egzemplarz -> " + egz.getNr_rejestracyjny());
        System.out.println("Model -> " + model.getId());


        model = jpaScooter.findSkuter(model.getId());
        jpaScooter.addEgzemplarz(egz, model);

        return "redirect:/admin/scooter";
    }

}
