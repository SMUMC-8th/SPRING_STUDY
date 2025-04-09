package com.example.umc8th.domain.article.service.command;

import com.example.umc8th.domain.article.dto.ArticleRequestDTO;
import com.example.umc8th.domain.article.entity.Article;

public interface ArticleCommandService {
    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);
    Article updateArticle(Long id, ArticleRequestDTO.UpdateArticleDTO dto);
    Article increaseLike(Long id);
    void deleteArticle(Long id);
}
