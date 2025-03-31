package com.example.umc8th.article.dto;


import com.example.umc8th.article.entity.Article;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


public class ArticleResponseDTO {

    @Builder
    @Data
    public static class ArticleDTO {
        private String content;
        private String title;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        public static ArticleDTO toDTO(Article article) {
            return ArticleDTO.builder()
                    .content(article.getContent())
                    .title(article.getTitle())
                    .createdAt(article.getCreatedAt())
                    .updatedAt(article.getUpdatedAt())
                    .build();
        }
    }
}
