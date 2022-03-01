package fr.easit.services;

import fr.easit.dto.ArticleDTO;
import fr.easit.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ArticleService{

    public abstract List<ArticleDTO> getArticles();
    public abstract List<ArticleDTO> getArticles(User user);
    public abstract List<String> getArticlesString();
}
