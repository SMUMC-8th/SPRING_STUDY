package com.example.umc8th.dto;

import lombok.Getter;

public class ReplyRequestDTO {
    @Getter
    public static class CreateReplyDTO {
        private String title;
        private String content;
    }
}
