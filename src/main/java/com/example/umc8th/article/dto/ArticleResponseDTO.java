package com.example.umc8th.article.dto;


import com.example.umc8th.article.entity.Article;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


public class ArticleResponseDTO {

    @Builder
    @Data
    public static class articleDTO {
        private String content;
        private String title;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        public static articleDTO toDTO(Article article) {
            return articleDTO.builder()
                    .content(article.getContent())
                    .title(article.getTitle())
                    .createdAt(article.getCreatedAt())
                    .updatedAt(article.getUpdatedAt())
                    .build();
        }
    }
}
