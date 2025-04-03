package com.example.umc8th.domain.article.service.command;

import com.example.umc8th.domain.article.dto.request.ArticleRequestDTO;
import com.example.umc8th.domain.article.dto.response.ArticleResponseDTO;

public interface ArticleCommandService {
    ArticleResponseDTO createArticle(ArticleRequestDTO.CreateArticleDTO dto);
}
