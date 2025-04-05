package umc.week3.code.reply.dto;

import umc.week3.code.reply.entity.Reply;

import java.time.LocalDateTime;

public class ReplyResponseDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReplyResponseDTO(Reply reply) {
        this.id = reply.getId();
        this.content = reply.getContent();
        this.createdAt = reply.getCreatedAt();
        this.updatedAt = reply.getUpdatedAt();
    }
}
