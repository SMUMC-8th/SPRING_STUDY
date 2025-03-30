package com.example.umc8th.reply.service.query;

import com.example.umc8th.reply.entity.Reply;

import java.util.List;

public interface ReplyQueryService {
    Reply getReplyById(long replyId);
    List<Reply> getReplies();
}
