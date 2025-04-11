package com.example.umc8th.service.query;

import com.example.umc8th.entity.Article;

import java.util.List;

public interface ArticleQueryService {
    Article getArticle(Long id);
    List<Article> getArticles();
}
