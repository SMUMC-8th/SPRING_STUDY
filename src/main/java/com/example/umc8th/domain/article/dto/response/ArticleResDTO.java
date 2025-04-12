package com.example.umc8th.domain.article.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleResDTO {

    @Builder
    public record CreateArticleResDTO(
            Long id,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record ArticlePreviewDTO(
            Long id,
            String title,
            String content,
            int likeNum,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }

    @Builder
    public record ArticlePreviewListDTO(
            List<ArticlePreviewDTO> articlePreviewDtoList,
            boolean hasNext,
            Long nextCursor
    ){
    }

    @Builder
    public record UpdateArticleResDTO(
            Long id,
            LocalDateTime updatedAt
    ){
    }

    @Builder
    public record DeleteArticleResDTO(
            Long id,
            LocalDateTime deletedAt
    ){
    }

    @Builder
    public record ArticleLikeResDTO(
            Long id,
            int likeNum
    ){
    }
}
