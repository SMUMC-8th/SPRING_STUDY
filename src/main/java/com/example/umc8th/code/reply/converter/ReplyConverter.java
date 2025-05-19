package com.example.umc8th.code.reply.converter;

import com.example.umc8th.code.article.entity.Article;
import com.example.umc8th.code.reply.dto.ReplyRequestDTO;
import com.example.umc8th.code.reply.entity.Reply;

public class ReplyConverter {
    public static Reply toEntity(ReplyRequestDTO.CreateReplyDTO dto, Article article) {
        return Reply.builder()
                .article(article)
                .content(dto.getContent())
                .build();
    }

}
