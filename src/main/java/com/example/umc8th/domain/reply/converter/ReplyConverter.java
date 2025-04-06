package com.example.umc8th.domain.reply.converter;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.reply.dto.reqeust.ReplyReqDTO;
import com.example.umc8th.domain.reply.dto.response.ReplyResDTO;
import com.example.umc8th.domain.reply.entity.Reply;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReplyConverter {

    // ReplyReqDTO.CreateReply -> Reply Entity
    public static Reply toReply(ReplyReqDTO.CreateReplyDTO replyDTO, Article article) {
        return Reply.builder()
                .content(replyDTO.getContent())
                .article(article)
                .build();
    }

    // Reply Entity -> Res.CreatedReplyDTO
    public static ReplyResDTO.CreateReplyDTO toCreateReplyDTO(Reply reply) {
        return ReplyResDTO.CreateReplyDTO.builder()
                .id(reply.getId())
                .createdAt(reply.getCreatedAt())
                .build();
    }

    // Reply Entity -> Res.PreviewReplyDTO
    public static ReplyResDTO.PreviewReplyDTO toPreviewReplyDTO(Reply reply) {
        return ReplyResDTO.PreviewReplyDTO.builder()
                .id(reply.getId())
                .articleId(reply.getArticle().getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .build();
    }

    // List<Reply> -> Res.PreviewListDTO
    public static ReplyResDTO.PreviewListReplyDTO toPreviewListReplyDTO(List<Reply> replies) {
        List<ReplyResDTO.PreviewReplyDTO> repliesDTO = replies.stream()
                .map(ReplyConverter::toPreviewReplyDTO).collect(Collectors.toList());

        return ReplyResDTO.PreviewListReplyDTO.builder()
                .replies(repliesDTO)
                .build();
    }
}
