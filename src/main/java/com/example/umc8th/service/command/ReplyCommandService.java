package com.example.umc8th.service.command;

import com.example.umc8th.dto.ReplyRequestDTO;
import com.example.umc8th.entity.Reply;

public interface ReplyCommandService {
    Reply createArticle(ReplyRequestDTO.CreateReplyDTO dto);
    public Long deleteReply(Long id);
    public Reply updateReply(Long id, ReplyRequestDTO.UpdateReplyDTO dto);
}
