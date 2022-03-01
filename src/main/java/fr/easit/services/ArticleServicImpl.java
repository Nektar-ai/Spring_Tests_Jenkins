package fr.easit.services;

import fr.easit.dto.ArticleDTO;
import fr.easit.models.Article;
import fr.easit.models.Client;
import fr.easit.models.User;
import fr.easit.repositories.ArticleRepository;
import fr.easit.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServicImpl implements ArticleService{

    @Autowired
    ArticleRepository articleRepository;


    @Override
    public List<ArticleDTO> getArticles(User user){
        List<Article> articles = articleRepository.findAll();
        List<ArticleDTO> articlesTrans = new ArrayList<>();

        for (Article a : articles) {
           articlesTrans.add(new ArticleDTO(a, user.getClient()));
        }
        return articlesTrans;
    }

    public List<ArticleDTO> getArticles(){
        List<Article> articles = articleRepository.findAll();
        List<ArticleDTO> articlesTrans = new ArrayList<>();



        for (Article a : articles) {
            articlesTrans.add(new ArticleDTO(a));
        }
        return articlesTrans;
    }

    public List<String> getArticlesString() {
        return null;
    }
}
