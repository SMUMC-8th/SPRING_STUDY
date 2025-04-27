package com.example.umc8th.service.query;


import com.example.umc8th.entity.Article;
import com.example.umc8th.entity.Reply;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReplyQueryService {
    Reply getReply(Long id);
    List<Reply> getReplies(Long articleId);
    Page<Reply> getRepliesByOffset(Long articleId, Integer page, Integer size);
}
