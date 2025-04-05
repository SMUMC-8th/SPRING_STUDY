package com.example.umc8th.service.command;


import com.example.umc8th.dto.ArticleRequestDTO;
import com.example.umc8th.entity.Article;

public interface ArticleCommandService {
    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);
}
