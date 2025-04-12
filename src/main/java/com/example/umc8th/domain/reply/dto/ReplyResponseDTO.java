package com.example.umc8th.domain.reply.dto;

import com.example.umc8th.domain.article.dto.ArticleResponseDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.reply.entity.Reply;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class ReplyResponseDTO {

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateReplyResponseDTO {
        private Long id;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReplyPreviewDTO {
        private Long id;
        private String content;
        private Long articleId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReplyPreviewListDTO {
        private List<ReplyPreviewDTO> replies;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class ReplyUpdateResponseDTO {
        private Long id;
        private LocalDateTime updatedAt;

        public static ReplyResponseDTO.ReplyUpdateResponseDTO from(Reply reply) {
            return ReplyResponseDTO.ReplyUpdateResponseDTO.builder()
                    .id(reply.getId())
                    .updatedAt(reply.getUpdatedAt())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class ReplyDeleteResponseDTO {
        private Long id;
        private LocalDateTime updatedAt;

        public static ReplyResponseDTO.ReplyDeleteResponseDTO from(Reply reply) {
            return ReplyResponseDTO.ReplyDeleteResponseDTO.builder()
                    .id(reply.getId())
                    .updatedAt(reply.getUpdatedAt())
                    .build();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReplyPreviewPageDTO {
        private List<ReplyPreviewDTO> replies;
    }
}
