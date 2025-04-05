package com.example.umc8th.article.service.query;

import com.example.umc8th.article.entity.Article;

import java.util.List;

public interface ArticleQueryService {
    Article getArticle(Long id);
    List<Article> getArticles();
}