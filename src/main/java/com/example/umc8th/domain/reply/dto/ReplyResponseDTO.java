package com.example.umc8th.domain.reply.dto;

import lombok.Getter;

import java.time.LocalDateTime;

public class ReplyResponseDTO {

    @Getter
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long articleId;
}
