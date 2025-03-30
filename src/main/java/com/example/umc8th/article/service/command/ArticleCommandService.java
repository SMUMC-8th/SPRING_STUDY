package com.example.umc8th.article.service.command;

import com.example.umc8th.article.dto.ArticleRequestDTO;
import com.example.umc8th.article.dto.ArticleResponseDTO;
import com.example.umc8th.article.entity.Article;

public interface ArticleCommandService {
    ArticleResponseDTO.articleDTO createArticle(Article article);
}
