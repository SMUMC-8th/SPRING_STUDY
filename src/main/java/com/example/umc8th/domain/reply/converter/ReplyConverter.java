package com.example.umc8th.domain.reply.converter;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc8th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc8th.domain.reply.entity.Reply;

import java.util.List;

public class ReplyConverter {

    // CreateReplyDTO -> Reply
    public static Reply toReply(ReplyRequestDTO.CreateReplyDTO dto, Article article) {
        return Reply.builder()
                .content(dto.content())
                .article(article)
                .build();
    }

    // Reply -> ReplyDTO
    public static ReplyResponseDTO.ReplyDTO toReplyDTO(Reply reply) {
        return ReplyResponseDTO.ReplyDTO.builder()
                .content(reply.getContent())
                .articleId(reply.getArticle().getId())
                .replyId(reply.getId())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .build();
    }

    // List<Reply> -> ReplyListDTO
    public static ReplyResponseDTO.ReplyListDTO toReplyListDTO(List<Reply> reply) {
        return ReplyResponseDTO.ReplyListDTO.builder()
                .replies(reply.stream()
                        .map(ReplyConverter::toReplyDTO)
                        .toList())
                .build();
    }

    // replyId -> DeleteReplyDTO
    public static ReplyResponseDTO.DeleteReplyDTO toDeleteReplyDTO(Long replyId) {
        return ReplyResponseDTO.DeleteReplyDTO.builder()
                .replyId(replyId)
                .build();
    }
}
