package fr.easit.services;

import fr.easit.dto.ArticleDTO;

import java.util.List;

public interface ArticleService{

    public abstract List<ArticleDTO> getArticles();
    public abstract List<String> getArticlesString();
}
