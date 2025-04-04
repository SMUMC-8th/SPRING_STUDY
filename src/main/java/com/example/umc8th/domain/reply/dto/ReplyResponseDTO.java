package com.example.umc8th.domain.reply.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ReplyResponseDTO {

    @Getter
    @Builder
    public static class ReplyDTO {
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Long articleId;
        private Long replyId;
    }

    @Getter
    @Builder
    public static class ReplyListDTO {
        private List<ReplyDTO> replies;
    }
}
