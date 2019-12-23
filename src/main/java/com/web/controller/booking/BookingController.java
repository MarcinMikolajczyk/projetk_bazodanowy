package com.web.controller.booking;

import com.entity.Klienci;
import com.entity.Wynajem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "/booking")
public class BookingController {

    @RequestMapping
    public String book(Model model){

        Wynajem wynajem = new Wynajem();
        Klienci klienci = new Klienci();

        model.addAttribute("wynajem", wynajem);
        model.addAttribute("klienci", klienci);

        return "booking/booking";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String book_next(@ModelAttribute Wynajem wynajem,
                            @ModelAttribute Klienci klienci,
                            RedirectAttributes model){

        model.addFlashAttribute("wynajem", wynajem);

        model.addAttribute("imie", klienci.getImie());
        model.addAttribute("nazwisko", klienci.getNazwisko());
        model.addAttribute("adres", klienci.getAdres());
        model.addAttribute("pesel", klienci.getPesel());
        model.addAttribute("nr_telefonu", klienci.getNr_telefonu());

        return "redirect:/booking/choose";
    }

}
