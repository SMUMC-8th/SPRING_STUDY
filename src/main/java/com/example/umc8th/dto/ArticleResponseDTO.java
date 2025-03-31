package com.example.umc8th.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ArticleResponseDTO {
    private String title;
    private String content;
    private int likeNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
