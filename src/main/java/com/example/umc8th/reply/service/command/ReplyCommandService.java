package com.example.umc8th.reply.service.command;

import com.example.umc8th.reply.dto.ReplyResponseDTO;
import com.example.umc8th.reply.entity.Reply;

public interface ReplyCommandService {
    ReplyResponseDTO.ReplyDTO createReply(Reply reply, Long articleId);
}
