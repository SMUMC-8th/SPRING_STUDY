package com.example.umc8th.domain.reply.service.query;

import com.example.umc8th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReplyQueryService {
    Page<Reply> getReplies(Long articleId, Integer page, Integer offset);
    Reply getReply(Long id);
}
