package com.example.umc8th.service;

import com.example.umc8th.entity.Article;
import com.example.umc8th.dto.ArticleRequestDTO;
import com.example.umc8th.entity.Reply;

public interface ArticleUpdateService {
    public Article updateArticle(Long id, ArticleRequestDTO.UpdateArticleDTO dto);
    public Article updateArticleContent(Long id, ArticleRequestDTO.UpdateArticleContentDTO dto);
}
