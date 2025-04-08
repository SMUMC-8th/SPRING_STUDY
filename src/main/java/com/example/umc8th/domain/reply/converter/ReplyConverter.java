package com.example.umc8th.domain.reply.converter;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.reply.dto.request.ReplyReqDTO;
import com.example.umc8th.domain.reply.dto.response.ReplyResDTO;
import com.example.umc8th.domain.reply.entity.Reply;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReplyConverter {

    // CreateReplyReqDTO -> Reply Entity
    public static Reply toReply(ReplyReqDTO.CreateReplyReqDTO resDTO, Article article) {
        return Reply.builder()
                .article(article)
                .content(resDTO.content())
                .deletedAt(null)
                .build();
    }

    // Reply Entity -> CreateReplyResDTO
    public static ReplyResDTO.CreateReplyResDTO toCreateReplyResponseDTO(Reply reply) {
        return ReplyResDTO.CreateReplyResDTO.builder()
                .id(reply.getId())
                .createdAt(reply.getCreatedAt())
                .build();
    }

    // Reply -> UpdateReplyResDTO
    public static ReplyResDTO.UpdateReplyResDTO toUpdateReplyResDTO(Reply reply) {
        return ReplyResDTO.UpdateReplyResDTO.builder()
                .id(reply.getId())
                .updatedAt(reply.getUpdatedAt())
                .build();
    }

    // Reply -> DeleteReplyResDTO
    public static ReplyResDTO.DeleteReplyResDTO toDeleteReplyResDTO(Reply reply) {
        return ReplyResDTO.DeleteReplyResDTO.builder()
                .id(reply.getId())
                .deletedAt(reply.getDeletedAt())
                .build();
    }

    // Reply -> ReplyPreviewDTO
    public static ReplyResDTO.ReplyPreviewDTO toReplyPreviewDTO(Reply reply) {
        return ReplyResDTO.ReplyPreviewDTO.builder()
                .id(reply.getId())
                .articleId(reply.getArticle().getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .build();
    }

    // List<Reply> -> ReplyPreviewListDTO
    public static ReplyResDTO.ReplyPreviewListDTO toReplyPreviewListDTO(List<Reply> replies) {
        return ReplyResDTO.ReplyPreviewListDTO.builder()
                .replies(replies.stream()
                        .map(ReplyConverter::toReplyPreviewDTO)
                        .toList())
                .build();
    }

    // Page<Reply> -> ReplyPreviewListDTO
    public static ReplyResDTO.ReplyPreviewListDTO toReplyPreviewListDTOWithPagination(Page<Reply> replyPage) {
        return ReplyResDTO.ReplyPreviewListDTO.builder()
                .replies(replyPage.getContent().stream()
                        .map(ReplyConverter::toReplyPreviewDTO)
                        .toList())
                .pageNo(replyPage.getNumber())
                .size(replyPage.getSize())
                .totalPages(replyPage.getTotalPages())
                .build();
    }
}
