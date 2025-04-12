package com.example.umc8th.domain.article.dto;


import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;


public class ArticleResponseDTO {

    @Builder
    public record ArticleDTO(
            Long articleId,
            int likeNum,
            String title,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    @Builder
    public record ArticleListDTO(List<ArticleDTO> articles) {}

    @Builder
    public record DeleteArticleDTO(Long articleId) {}

    @Builder
    public record PageArticleDTO(
            ArticleListDTO result,
            String cursor,
            boolean hasNext,
            int size
    ) {}
}
