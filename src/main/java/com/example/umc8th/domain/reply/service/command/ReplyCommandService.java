package com.example.umc8th.domain.reply.service.command;

import com.example.umc8th.domain.reply.dto.request.ReplyReqDTO;
import com.example.umc8th.domain.reply.dto.response.ReplyResDTO;

public interface ReplyCommandService {

    ReplyResDTO.CreateReplyResDTO createReply(ReplyReqDTO.CreateReplyReqDTO reqDTO);
}
