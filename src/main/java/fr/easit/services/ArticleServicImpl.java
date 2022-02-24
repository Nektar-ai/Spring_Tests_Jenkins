package fr.easit.services;

import fr.easit.dao.ArticleDAO;
import fr.easit.models.Article;
import fr.easit.models.Client;
import fr.easit.models.User;
import fr.easit.repositories.ArticleRepository;
import fr.easit.repositories.ClientRepository;
import fr.easit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServicImpl implements ArticleService{

    @Autowired
    ArticleRepository articleRepository;
    //ClientRepository clientRepository;

    @Override
    public List<ArticleDAO> getArticles(){
        List<Article> articles = articleRepository.findAll();
        List<ArticleDAO> articlesTrans = new ArrayList<>();
        for (Article a : articles) {
           articlesTrans.add(new ArticleDAO(a));
        }
        return articlesTrans;
    }

    public List<String> getArticlesString() {
        return null;
    }
}
