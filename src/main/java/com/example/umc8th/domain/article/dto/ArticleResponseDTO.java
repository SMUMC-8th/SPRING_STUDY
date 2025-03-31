package com.example.umc8th.domain.article.dto;

import lombok.Getter;

import java.time.LocalDateTime;

public class ArticleResponseDTO {

    @Getter
    private Long id;
    private String title;
    private String content;
    private int likeNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
