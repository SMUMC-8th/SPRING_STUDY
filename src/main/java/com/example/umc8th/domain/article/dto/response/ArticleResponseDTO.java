package com.example.umc8th.domain.article.dto.response;

import com.example.umc8th.domain.article.entity.Article;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ArticleResponseDTO {
    private Long id;
    private String title;
    private String content;
    private Integer likeNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ArticleResponseDTO ArticleConverter(Article article) {
        return ArticleResponseDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }
}

