package com.example.umc8th.domain.article.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class ArticleReqDTO {

    @Getter
    public static class CreateArticleDTO {
        @NotBlank
        private String title;

        @NotBlank
        private String content;
    }
}