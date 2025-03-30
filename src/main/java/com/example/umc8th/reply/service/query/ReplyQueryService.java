package com.example.umc8th.reply.service.query;

import com.example.umc8th.reply.dto.ReplyResponseDTO;
import com.example.umc8th.reply.entity.Reply;

import java.util.List;

public interface ReplyQueryService {
    List<ReplyResponseDTO.ReplyDTO> getReplyList(Long articleId);
}
