package umc.week3.code.reply.service.command;

import umc.week3.code.reply.dto.ReplyRequestDTO;
import umc.week3.code.reply.entity.Reply;

public interface ReplyCommandService {
    Reply createReply(ReplyRequestDTO.CreateReplyDTO dto);
}
