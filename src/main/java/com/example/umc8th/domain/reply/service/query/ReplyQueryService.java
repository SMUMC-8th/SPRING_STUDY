package com.example.umc8th.domain.reply.service.query;

import com.example.umc8th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReplyQueryService {
    List<Reply> getReplies(Long articleId);
    Reply getReply(Long id);
    Boolean existsReply(Long articleId);
    Page<Reply> getRepliesPageable(Long articleId, int page, int size);
}
