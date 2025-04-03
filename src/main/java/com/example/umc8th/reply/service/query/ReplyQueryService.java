package com.example.umc8th.reply.service.query;

import com.example.umc8th.reply.dto.ReplyResponseDTO;

public interface ReplyQueryService {
    ReplyResponseDTO.ReplyListDTO getReplyList(Long articleId);
}
