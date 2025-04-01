package com.example.umc8th.service.query;

import com.example.umc8th.entity.Reply;

import java.util.List;

public interface ReplyQueryService {

    List<Reply> getReplies();
    Reply getReply(Long id);
}
