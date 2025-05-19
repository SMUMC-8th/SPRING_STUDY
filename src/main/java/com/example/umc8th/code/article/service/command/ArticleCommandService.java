package com.example.umc8th.code.article.service.command;

import com.example.umc8th.code.article.dto.ArticleRequestDTO;
import com.example.umc8th.code.article.entity.Article;

public interface ArticleCommandService {
    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);
    Article saveAndUpdate(Long id, ArticleRequestDTO.CreateArticleDTO dto);

    Article findActiveArticle(Long id);
    void deleteArticle(Long id);
}
