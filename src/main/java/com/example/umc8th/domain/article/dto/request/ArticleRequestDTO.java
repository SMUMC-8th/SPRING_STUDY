package com.example.umc8th.domain.article.dto.request;

import com.example.umc8th.domain.article.entity.Article;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class ArticleRequestDTO {

    @Getter
    public static class CreateArticleDTO {
        @NotBlank
        private String title;

        @NotBlank
        private String content;

        public Article toEntity() {
            return Article.builder()
                    .title(this.title)
                    .content(this.content)
                    .likeNum(0)
                    .build();
        }
    }
}