package com.example.umc8th.domain.reply.dto.response;

import com.example.umc8th.domain.reply.entity.Reply;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ReplyResDTO {

    @Builder
    public record CreateReplyDTO(
            Long id,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record PreviewReplyDTO(
            Long id,
            Long articleId,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    @Builder
    public record PreviewListReplyDTO(
            List<PreviewReplyDTO> replies
    ) {}
}
