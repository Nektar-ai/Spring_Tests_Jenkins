package fr.easit.repositories;

import fr.easit.models.Article;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Configuration
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findAll();
}