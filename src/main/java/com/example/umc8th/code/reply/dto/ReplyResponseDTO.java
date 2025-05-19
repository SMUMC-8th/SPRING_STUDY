package com.example.umc8th.code.reply.dto;

import com.example.umc8th.code.reply.entity.Reply;

import java.time.LocalDateTime;

public class ReplyResponseDTO {
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReplyResponseDTO(Reply reply){
        this.content = reply.getContent();
        this.createdAt = reply.getCreatedAt();
        this.updatedAt = reply.getUpdatedAt();
    }

}
