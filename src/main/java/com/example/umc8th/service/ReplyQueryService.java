package com.example.umc8th.service;


import com.example.umc8th.entity.Article;
import com.example.umc8th.entity.Reply;

import java.util.List;

public interface ReplyQueryService {
    Reply getReply(Long id);
    List<Reply> getReplies();
}
