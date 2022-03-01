package fr.easit.controllers;

import fr.easit.services.ArticleService;
import fr.easit.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientController {
    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public String clients(Model model) {
        model.addAttribute("clientList", clientService.findAll());
        return "clients";
    }
}
