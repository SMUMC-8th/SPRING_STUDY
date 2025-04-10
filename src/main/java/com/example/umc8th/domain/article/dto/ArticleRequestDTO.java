package com.example.umc8th.domain.article.dto;

public class ArticleRequestDTO {

    public record CreateArticleDTO(String title, String content) {}

    public record UpdateArticleDTO(String title, String content) {}

}
