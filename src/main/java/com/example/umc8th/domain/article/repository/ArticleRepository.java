package com.example.umc8th.domain.article.repository;

import com.example.umc8th.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
