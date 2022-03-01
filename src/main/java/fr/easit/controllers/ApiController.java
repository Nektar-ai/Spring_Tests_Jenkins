package fr.easit.controllers;

import fr.easit.dto.ArticleDTO;
import fr.easit.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/api/articles", method = RequestMethod.GET)
    public List<ArticleDTO> getArticlesJson() {
        return articleService.getArticles();
    }
}
