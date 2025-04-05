package com.example.umc8th.code.article.service.query;

import com.example.umc8th.code.article.entity.Article;

import java.util.List;

public interface ArticleQueryService {
    List<Article> getArticles();
    Article getArticle(Long id);
}
