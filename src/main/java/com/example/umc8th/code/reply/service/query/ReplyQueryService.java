package com.example.umc8th.code.reply.service.query;

import com.example.umc8th.code.reply.entity.Reply;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReplyQueryService {

    List<Reply> getReplies();
    List<Reply> getRepliesByArticle(Long articleId);
//    Reply getReply(Long id);
    Reply findActiveReply(Long articleId, Long replyId);


    Page<Reply> findRepliesByArticleId(Long articleId, int page, int size);

}
