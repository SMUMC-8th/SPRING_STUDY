package com.example.umc8th.domain.reply.service.command;

import com.example.umc8th.domain.reply.dto.reqeust.ReplyReqDTO;
import com.example.umc8th.domain.reply.dto.response.ReplyResDTO;
import com.example.umc8th.domain.reply.entity.Reply;

public interface ReplyCommandService {
    ReplyResDTO.CreateReplyDTO createReply(ReplyReqDTO.CreateReplyDTO dto, Long articleId);
}
