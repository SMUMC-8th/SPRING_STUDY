package umc.week3.code.service.command;

import umc.week3.code.dto.ReplyRequestDTO;
import umc.week3.code.entity.Reply;

public interface ReplyCommandService {
    Reply createReply(ReplyRequestDTO.CreateReplyDTO dto);
}
