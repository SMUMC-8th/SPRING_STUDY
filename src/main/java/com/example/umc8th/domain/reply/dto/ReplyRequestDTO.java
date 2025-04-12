package com.example.umc8th.domain.reply.dto;

public class ReplyRequestDTO {

    public record CreateReplyDTO(String content) {}

    public record UpdateReplyDTO(String content) {}

}
