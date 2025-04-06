package com.example.umc8th.domain.article.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


public class ArticleResponseDTO {

    @Getter
    @Builder
    public static class ArticleDTO {
        private Long articleId;
        private String content;
        private String title;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Builder
    public static class ArticleListDTO {
        private List<ArticleDTO> articles;
    }

    @Getter
    @Builder
    public static class DeleteArticleDTO{
        private Long articleId;
    }
}
