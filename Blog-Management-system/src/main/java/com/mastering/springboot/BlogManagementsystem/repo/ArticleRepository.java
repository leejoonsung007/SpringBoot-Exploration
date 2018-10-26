package com.mastering.springboot.BlogManagementsystem.repo;

import com.mastering.springboot.BlogManagementsystem.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface ArticleRepository extends ElasticsearchRepository<Article,String> {
    Optional<Article> findByLink(String link);
    Page<Article> findByTitleContainingAndBodyContaining(String title, String body, Pageable pageable);
}
