package com.example.umc8th.domain.article.converter;

import com.example.umc8th.domain.article.dto.ArticleRequestDTO;
import com.example.umc8th.domain.article.dto.ArticleResponseDTO;
import com.example.umc8th.domain.article.entity.Article;

import java.util.List;

public class ArticleConverter {

    // CreateArticleDTO -> Article
    public static Article toArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        return Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    // Article -> ArticleDTO
    public static ArticleResponseDTO.ArticleDTO toArticleDTO(Article article) {
        return ArticleResponseDTO.ArticleDTO.builder()
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .articleId(article.getId())
                .build();
    }

    // List<Article> -> ArticleListDTO
    public static ArticleResponseDTO.ArticleListDTO toArticleListDTO(List<Article> articles) {
        return ArticleResponseDTO.ArticleListDTO.builder()
                .articles(articles.stream()
                        .map(ArticleConverter::toArticleDTO)
                        .toList())
                .build();
    }
}
