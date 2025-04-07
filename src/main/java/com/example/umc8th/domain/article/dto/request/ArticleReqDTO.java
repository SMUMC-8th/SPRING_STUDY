package com.example.umc8th.domain.article.dto.request;

public class ArticleReqDTO {

    public record CreateArticleReqDTO(
            String title,
            String content
    ) {
    }

    public record UpdateArticleReqDTO(
            String title,
            String content
    ){
    }
}
