package fr.easit.controllers;

import fr.easit.models.User;
import fr.easit.services.ArticleService;
import fr.easit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
public class HomeController {
    Logger logger = Logger.getLogger("HomeController");
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, Authentication auth) {
        User user = userService.findUserByUsername(auth.getPrincipal().toString()).get();
        model.addAttribute("articles", articleService.getArticles(user));

        return "index";
    }

}
