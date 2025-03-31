package com.example.umc8th.reply.service.query;

import com.example.umc8th.reply.dto.ReplyResponseDTO;

import java.util.List;

public interface ReplyQueryService {
    List<ReplyResponseDTO.ReplyDTO> getReplyList(Long articleId);
}
