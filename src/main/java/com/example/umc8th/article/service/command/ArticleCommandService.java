package com.example.umc8th.article.service.command;

import com.example.umc8th.article.dto.ArticleRequestDTO;
import com.example.umc8th.article.dto.ArticleResponseDTO;

public interface ArticleCommandService {
    ArticleResponseDTO.ArticleDTO createArticle(ArticleRequestDTO.CreateArticleDTO dto);
}
