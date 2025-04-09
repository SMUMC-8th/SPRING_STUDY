package com.example.umc8th.domain.reply.dto;

import lombok.Getter;


public class ReplyRequestDTO {

    public record CreateReplyDTO(String content) {}

    public record UpdateReplyDTO(String content) {}

}
