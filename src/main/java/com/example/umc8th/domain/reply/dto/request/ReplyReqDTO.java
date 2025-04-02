package com.example.umc8th.domain.reply.dto.request;

public class ReplyReqDTO {

    public record CreateReplyReqDTO(
            Long articleId,
            String content
    ) {
    }

    public record UpdateReplyReqDTO(
            String content
    ){
    }
}
