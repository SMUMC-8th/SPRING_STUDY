package com.example.umc8th.reply.service.query;

import com.example.umc8th.article.entity.Article;
import com.example.umc8th.reply.entity.Reply;

import java.util.List;

public interface ReplyQueryService {
    Reply getReply(Long id);
    List<Reply> getRepliesByArticleId(Long articleId);
}
