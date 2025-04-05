package com.example.umc8th.reply.service.command;

import com.example.umc8th.reply.dto.ReplyRequestDTO;
import com.example.umc8th.reply.entity.Reply;

public interface ReplyCommandService {
    Reply createReply(ReplyRequestDTO.CreateReplyDTO dto, Long articleId);
}
