package com.example.umc8th.reply.dto;

import lombok.Getter;

public class ReplyRequestDTO {

    @Getter
    public static class CreateReplyDTO{
        private String content;
    }
}
