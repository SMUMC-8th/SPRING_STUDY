package com.example.umc8th.domain.reply.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReplyResDTO {

    @Builder
    public record CreateReplyResDTO(
            Long id,
            LocalDateTime createdAt
    ){
    }

    @Builder
    public record ReplyPreviewDTO(
            Long id,
            Long articleId,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }

    @Builder
    public record ReplyPreviewListDTO(
            List<ReplyPreviewDTO> replies,
            int pageNo,
            int size,
            int totalPages
    ) {
    }

    @Builder
    public record UpdateReplyResDTO(
            Long id,
            LocalDateTime updatedAt
    ){
    }

    @Builder
    public record DeleteReplyResDTO(
            Long id,
            LocalDateTime deletedAt
    ){
    }
}
