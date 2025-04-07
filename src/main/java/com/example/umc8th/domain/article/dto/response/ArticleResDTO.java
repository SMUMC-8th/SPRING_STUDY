package com.example.umc8th.domain.article.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleResDTO {

    @Builder
    public record CreateArticleDTO(
            Long id,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record ArticlePreviewDTO(
            Long id,
            String title,
            String content,
            Integer likeNum,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ){
    }

    @Builder
    public record ArticlePreviewListDTO(
            List<ArticlePreviewDTO> articlePreviewListDTO
    ){
    }

    @Builder
    public record UpdateArticleDTO(
            Long id,
            LocalDateTime updatedAt
    ){
    }

    @Builder
    public record DeleteArticleDTO(
            Long id
    ){
    }
}

