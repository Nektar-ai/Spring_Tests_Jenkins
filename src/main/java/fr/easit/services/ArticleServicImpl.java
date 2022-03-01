package fr.easit.services;

import fr.easit.dto.ArticleDTO;
import fr.easit.models.Article;
import fr.easit.models.Client;
import fr.easit.repositories.ArticleRepository;
import fr.easit.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServicImpl implements ArticleService{

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<ArticleDTO> getArticles(){
        List<Article> articles = articleRepository.findAll();
        List<ArticleDTO> articlesTrans = new ArrayList<>();

        Client client = clientRepository.getById(5);
        for (Article a : articles) {
           articlesTrans.add(new ArticleDTO(a, client));
        }
        return articlesTrans;
    }

    public List<String> getArticlesString() {
        return null;
    }
}
