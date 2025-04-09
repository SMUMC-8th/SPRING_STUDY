package com.example.umc8th.domain.reply.service.query;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc8th.domain.reply.entity.Reply;

public interface ReplyQueryService {
    Reply isReplyExistInArticle(Long articleId, Long replyId);
    ReplyResponseDTO.PageReplyDTO getReplyList(Long articleId, int page, int size);
}
