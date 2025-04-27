package com.example.umc8th.dto;

import com.example.umc8th.entity.Article;
import lombok.*;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleResponseDTO {

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class CreateArticleResponseDTO {
        private Long id;
        private LocalDateTime createdAt;

        public static CreateArticleResponseDTO from(Article article) {
            return CreateArticleResponseDTO.builder()
                    .id(article.getId())
                    .createdAt(article.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class ArticlePreviewDTO {
        private Long id;
        private String title;
        private String content;
        private int likeNum;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static ArticlePreviewDTO from(Article article) {
            return ArticlePreviewDTO.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .likeNum(article.getLikeNum())
                    .createdAt(article.getCreatedAt())
                    .updatedAt(article.getUpdatedAt())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class ArticlePreviewListDTO {
        private List<ArticlePreviewDTO> articles;
        private boolean hasNext;
        private Long cursor;
        public static ArticlePreviewListDTO from(Slice<Article> articles) {
            return ArticlePreviewListDTO.builder()
                    .articles(articles.stream().map(ArticlePreviewDTO::from).toList())
                    .hasNext(articles.hasNext())
                    .cursor(articles.getContent().isEmpty()? 0:articles.getContent().get(articles.getContent().size()-1).getId())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class ArticleDeleteDTO {
        private Long id;
        public static ArticleDeleteDTO from(Long id) {
            return ArticleDeleteDTO.builder().
                    id(id)
                    .build();
        }
    }
}