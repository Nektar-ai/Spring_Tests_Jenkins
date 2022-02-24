package fr.easit.services;

import fr.easit.dao.ArticleDAO;
import fr.easit.models.Article;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArticleService{

    public abstract List<ArticleDAO> getArticles();
    public abstract List<String> getArticlesString();
}
