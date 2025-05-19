package com.example.umc8th.code.reply.dto;

import lombok.Getter;

public class ReplyRequestDTO {

    @Getter
    public static class CreateReplyDTO {
        private Long articleId;
        private String content;
    }

    @Getter
    public static class UpdateReplyDTO {
        private Long replyId;
        private String content;
    }
}
