package com.example.umc8th.service;

import com.example.umc8th.dto.ReplyRequestDTO;
import com.example.umc8th.entity.Reply;

public interface ReplyUpdateService {
    public Reply updateReply(Long id, ReplyRequestDTO.UpdateReplyDTO dto);
}
