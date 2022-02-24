package fr.easit.controllers;

import fr.easit.services.ArticleService;
import fr.easit.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ClientController {
    @Autowired
    ArticleService articleService;
    ClientService clientService;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String clients(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "clients";
    }
}
