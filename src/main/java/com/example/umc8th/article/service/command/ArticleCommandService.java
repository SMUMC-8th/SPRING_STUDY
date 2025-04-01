package com.example.umc8th.article.service.command;

import com.example.umc8th.article.dto.ArticleResponseDTO;
import com.example.umc8th.article.entity.Article;

public interface ArticleCommandService {
    ArticleResponseDTO.ArticleDTO createArticle(Article article);
}
