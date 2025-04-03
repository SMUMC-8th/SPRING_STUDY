package com.example.umc8th.reply.service.command;

import com.example.umc8th.reply.dto.ReplyRequestDTO;
import com.example.umc8th.reply.dto.ReplyResponseDTO;

public interface ReplyCommandService {
    ReplyResponseDTO.ReplyDTO createReply(ReplyRequestDTO.CreateReplyDTO dto, Long articleId);
}
