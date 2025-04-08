package com.example.umc8th.domain.article.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ArticleReqDTO {

//    @Getter
//    public static class CreateArticleDTO {
//        @NotBlank
//        private String title;
//
//        @NotBlank
//        private String content;
//    }
    @Builder
    public record CreateArticleDTO(
            String title,
            String content
    ){
    }

    @Builder
    public record UpdateArticleDTO(
            String title,
            String content
    ) {
    }

    @Builder
    public record UpdatePutArticleDTO(
            @NotBlank String title,
            @NotBlank String content
    ) {
    }
}