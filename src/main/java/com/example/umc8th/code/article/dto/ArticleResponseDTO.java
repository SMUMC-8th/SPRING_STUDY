package com.example.umc8th.code.article.dto;

import com.example.umc8th.code.article.entity.Article;

import java.time.LocalDateTime;

public class ArticleResponseDTO {
    private Long id;
    private String title;
    private String content;
    private int likeNum;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public ArticleResponseDTO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.likeNum = article.getLikeNum();
        this.createAt = article.getCreateAt();
    }

}
