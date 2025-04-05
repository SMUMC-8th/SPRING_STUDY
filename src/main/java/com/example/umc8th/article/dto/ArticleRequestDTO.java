package com.example.umc8th.article.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class ArticleRequestDTO {

    @Getter
    public static class CreateArticleDTO {
        @NotBlank
        private String title;

        @NotBlank
        private String content;
    }
}