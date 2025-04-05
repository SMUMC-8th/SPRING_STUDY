package com.example.umc8th.service.command;


import com.example.umc8th.dto.ReplyRequestDTO;
import com.example.umc8th.entity.Reply;

public interface ReplyCommandService {
    Reply createReply(ReplyRequestDTO.CreateReplyDTO dto);
}
