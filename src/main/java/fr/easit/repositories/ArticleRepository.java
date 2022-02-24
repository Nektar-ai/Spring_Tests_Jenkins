package fr.easit.repositories;

import fr.easit.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    public List<Article> findAll();
}