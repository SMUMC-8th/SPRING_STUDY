package com.example.umc8th.domain.reply.service.command;

import com.example.umc8th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc8th.domain.reply.dto.ReplyResponseDTO;

public interface ReplyCommandService {
    ReplyResponseDTO.ReplyDTO createReply(ReplyRequestDTO.CreateReplyDTO dto, Long articleId);
    ReplyResponseDTO.ReplyDTO updateReply(ReplyRequestDTO.UpdateReplyDTO dto, Long articleId, Long replyId);
    ReplyResponseDTO.DeleteReplyDTO deleteReply(Long articleId, Long replyId);
}
