package fr.easit.controllers;

import fr.easit.models.User;
import fr.easit.services.ArticleService;
import fr.easit.services.ClientService;
import fr.easit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
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
