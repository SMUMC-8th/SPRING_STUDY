package com.example.umc8th.domain.reply.service.query;

import com.example.umc8th.domain.reply.dto.ReplyResponseDTO;

public interface ReplyQueryService {
    ReplyResponseDTO.ReplyListDTO getReplyList(Long articleId);
}
