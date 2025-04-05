package com.example.umc8th.code.article.converter;

import com.example.umc8th.code.article.dto.ArticleRequestDTO;
import com.example.umc8th.code.article.entity.Article;

public class ArticleConverter {
    public static Article toEntity(ArticleRequestDTO.CreateArticleDTO dto) {
        return Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }
}

