package com.example.umc8th.reply.dto;

import com.example.umc8th.reply.entity.Reply;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReplyResponseDTO {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long articleId;

    public static ReplyResponseDTO ReplyConverter(Reply reply) {
        return ReplyResponseDTO.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .articleId(reply.getArticle().getId())
                .build();
    }

}
