package com.web.controller.admin.clients;

import com.data.JpaClient;
import com.entity.Klienci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.TypedQuery;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/clients")
public class ClientsController {

    @Autowired
    private JpaClient jpaClient;

    @RequestMapping
    public String clients(Model model){
        List<Klienci> klienci;

        TypedQuery<Klienci> query = jpaClient.getEn().createQuery("select k from Klienci k", Klienci.class);
        klienci = query.getResultList();

        model.addAttribute("klienci", klienci);

        return "admin/clients/clients";
    }

    @RequestMapping(value = "/delete/{id}")
    public String clients_delete(@PathVariable int id, Model model){

        jpaClient.deleteKlient(id);
        return "redirect:/admin/clients";
    }

    @RequestMapping(value = "/next/{id}")
    public String clients_next(@PathVariable int id, Model model){
        Klienci klienci;

        TypedQuery<Klienci> query = jpaClient.getEn().createQuery("select k from Klienci k where k.id = :id", Klienci.class).setParameter("id", id);
        klienci = query.getSingleResult();

        model.addAttribute("wynajmy", klienci.getWynajmy());

        return "admin/clients/next";
    }

    @RequestMapping(value = "/next/delete/{id}")
    public String clients_next_delete(@PathVariable int id, Model model){

        jpaClient.deleteWynajem(id);
        return "redirect:/admin/clients";
    }


}
