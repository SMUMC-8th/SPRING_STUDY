package com.example.umc8th.domain.reply.dto;

import lombok.Getter;


public class ReplyRequestDTO {

    @Getter
    public static class CreateReplyDTO{
        private String content;
    }

    @Getter
    public static class UpdateReplyDTO{
        private String content;
    }
}
