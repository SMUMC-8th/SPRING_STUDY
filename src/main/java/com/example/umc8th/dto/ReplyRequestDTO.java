package com.example.umc8th.dto;

import lombok.Getter;

public class ReplyRequestDTO {

    @Getter
    public static class CreateReplyDTO {
        private Long articleId;
        private String content;
    }
}
