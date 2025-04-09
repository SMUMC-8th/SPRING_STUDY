package com.example.umc8th.domain.article.dto;

import lombok.Getter;

public class ArticleRequestDTO {

    public record CreateArticleDTO(String title, String content) {}

    public record UpdateArticleDTO(String title, String content) {}
}
