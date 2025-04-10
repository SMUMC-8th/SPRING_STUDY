package com.example.umc8th.domain.article.dto;


import com.example.umc8th.domain.article.entity.Article;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.time.LocalDate;
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
