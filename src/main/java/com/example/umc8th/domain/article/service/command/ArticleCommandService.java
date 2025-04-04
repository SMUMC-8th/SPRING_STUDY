package com.example.umc8th.domain.article.service.command;

import com.example.umc8th.domain.article.dto.ArticleRequestDTO;
import com.example.umc8th.domain.article.dto.ArticleResponseDTO;

public interface ArticleCommandService {
    ArticleResponseDTO.ArticleDTO createArticle(ArticleRequestDTO.CreateArticleDTO dto);
    ArticleResponseDTO.ArticleDTO updateArticleAll(ArticleRequestDTO.UpdateArticleDTO dto, Long articleId);
    ArticleResponseDTO.ArticleDTO updateArticleContent(ArticleRequestDTO.UpdateArticleDTO dto, Long articleId);
    ArticleResponseDTO.ArticleDTO updateArticleTitle(ArticleRequestDTO.UpdateArticleDTO dto, Long articleId);
}
