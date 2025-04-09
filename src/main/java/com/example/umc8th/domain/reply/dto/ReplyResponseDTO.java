package com.example.umc8th.domain.reply.dto;

import com.example.umc8th.domain.reply.entity.Reply;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class ReplyResponseDTO {

    @Builder
    public record ReplyDTO(
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Long articleId,
            Long replyId
    ) {}

    @Builder
    public record ReplyListDTO(List<ReplyDTO> replies) {}

    @Builder
    public record DeleteReplyDTO(Long replyId) {}

    @Builder
    public record PageReplyDTO(
            ReplyListDTO result,
            int page,
            int totalPage
    ) {}
}
