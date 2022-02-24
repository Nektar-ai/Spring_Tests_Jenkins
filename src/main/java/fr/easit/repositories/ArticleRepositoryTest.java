package fr.easit.repositories;

import fr.easit.models.Article;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepositoryTest extends PagingAndSortingRepository<Article, Integer> {

    @Override
    Iterable<Article> findAll();
}