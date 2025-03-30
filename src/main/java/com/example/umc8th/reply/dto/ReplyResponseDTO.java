package com.example.umc8th.reply.dto;

import com.example.umc8th.article.entity.Article;
import com.example.umc8th.reply.entity.Reply;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReplyResponseDTO {

    @Getter
    @Builder
    public static class ReplyDTO {
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Long articleId;

        public static ReplyDTO toDTO(Reply reply) {
            return ReplyDTO.builder()
                    .createdAt(reply.getCreatedAt())
                    .updatedAt(reply.getUpdatedAt())
                    .content(reply.getContent())
                    .articleId(reply.getArticle().getId())
                    .build();
        }
    }
}
