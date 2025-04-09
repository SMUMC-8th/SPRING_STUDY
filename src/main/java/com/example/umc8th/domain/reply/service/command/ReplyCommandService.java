package com.example.umc8th.domain.reply.service.command;

import com.example.umc8th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc8th.domain.reply.entity.Reply;

public interface ReplyCommandService {
    Reply createReply(ReplyRequestDTO.CreateReplyDTO dto);
    Reply updateReply(Long id, ReplyRequestDTO.UpdateReplyDTO dto);
    Long deleteReply(Long id);
    void cancelDelete(Long id);
}
