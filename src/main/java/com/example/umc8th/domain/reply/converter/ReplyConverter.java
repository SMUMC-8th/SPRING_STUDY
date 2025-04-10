package com.example.umc8th.domain.reply.converter;


import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc8th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc8th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;

import java.util.List;

public class ReplyConverter {

    public static Reply toReply(ReplyRequestDTO.CreateReplyDTO dto, Article article) {
        return Reply.builder()
                .content(dto.getContent())
                .article(article)
                .build();
    }

    public static ReplyResponseDTO.CreateReplyResponseDTO toCreateReplyResponseDTO(Reply reply) {
        return ReplyResponseDTO.CreateReplyResponseDTO.builder()
                .id(reply.getId())
                .createdAt(reply.getCreatedAt())
                .build();
    }

    public static ReplyResponseDTO.ReplyPreviewDTO toReplyPreviewDTO(Reply reply) {
        return ReplyResponseDTO.ReplyPreviewDTO.builder()
                .id(reply.getId())
                .content(reply.getDeletedAt() == null ? "Deleted Reply" : reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .articleId(reply.getArticle().getId())
                .build();
    }

    public static ReplyResponseDTO.ReplyPreviewListDTO toReplyPreviewListDTO(Page<Reply> replies) {
        return ReplyResponseDTO.ReplyPreviewListDTO.builder()
                .replies(replies.getContent().stream().map(ReplyConverter::toReplyPreviewDTO).toList())
                .pageNo(replies.getNumber() + 1)
                .size(replies.getSize())
                .totalPage(replies.getTotalPages())
                .build();
    }
}
