package com.example.umc8th.domain.reply.service.query;

import com.example.umc8th.domain.reply.entity.Reply;

import java.util.List;

public interface ReplyQueryService {
    List<Reply> getReplies(Long articleId);
    Reply getReply(Long id);
}
