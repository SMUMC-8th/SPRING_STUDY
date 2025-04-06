package com.example.umc8th.domain.reply.service.query;

import com.example.umc8th.domain.reply.entity.Reply;

import java.util.List;

public interface ReplyQueryService {
    Reply getReply(Long id);
    List<Reply> getAllReplies();

    // 특정 아티클id로 딸린 댓글 조회하기
    //List<Reply> getRepliesInArticle(Long articleId);
}
