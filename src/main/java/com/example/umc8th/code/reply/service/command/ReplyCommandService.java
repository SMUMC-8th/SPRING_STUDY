package com.example.umc8th.code.reply.service.command;

import com.example.umc8th.code.article.dto.ArticleRequestDTO;
import com.example.umc8th.code.article.entity.Article;
import com.example.umc8th.code.reply.dto.ReplyRequestDTO;
import com.example.umc8th.code.reply.entity.Reply;

public interface ReplyCommandService {
    Reply createReply(ReplyRequestDTO.CreateReplyDTO dto, Long articleId);
    Reply saveAndUpdate(Long articleId, ReplyRequestDTO.UpdateReplyDTO dto);
    void deleteReply(Long articleId, Long replyId);
}
