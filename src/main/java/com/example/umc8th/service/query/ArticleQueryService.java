package com.example.umc8th.service.query;

import com.example.umc8th.entity.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ArticleQueryService {
    Article getArticle(Long id);
    List<Article> getArticles();
    Page<Article> getArticlesByCursor(Integer cursor, Integer size);
}
