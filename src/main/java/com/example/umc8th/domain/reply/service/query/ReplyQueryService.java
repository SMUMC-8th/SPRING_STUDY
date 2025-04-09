package com.example.umc8th.domain.reply.service.query;

import com.example.umc8th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc8th.domain.reply.entity.Reply;

public interface ReplyQueryService {
    ReplyResponseDTO.ReplyListDTO getReplyList(Long articleId);
    Reply isReplyExistInArticle(Long articleId, Long replyId);
}
