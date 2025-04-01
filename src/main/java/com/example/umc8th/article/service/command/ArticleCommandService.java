package com.example.umc8th.article.service.command;

import com.example.umc8th.article.dto.ArticleRequestDTO;
import com.example.umc8th.article.dto.ArticleResponseDTO;

public interface ArticleCommandService {
    ArticleResponseDTO createArticle(ArticleRequestDTO.CreateArticleDTO dto);
}
