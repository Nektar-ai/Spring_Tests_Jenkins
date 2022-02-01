package fr.easit.easit.controllers;

import fr.easit.easit.models.Account;
import fr.easit.easit.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ControllerTest {

/*    @Autowired
    private AccountService accountService;*/

    @GetMapping("/test")
    public String findCities(Model model) {

/*        List<Account> list =  accountService.findAll();*/

/*        model.addAttribute("accounts", list);*/

        return "test";
    }
}
