package com.example.umc8th.service.command;

import com.example.umc8th.dto.ArticleRequestDTO;
import com.example.umc8th.entity.Article;

public interface ArticleCommandService {
    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);
    public Article updateArticle(Long id, ArticleRequestDTO.UpdateArticleDTO dto);
    public Article updateArticleContent(Long id, ArticleRequestDTO.UpdateArticleContentDTO dto);
    public Long deleteArticle(Long id);
}
