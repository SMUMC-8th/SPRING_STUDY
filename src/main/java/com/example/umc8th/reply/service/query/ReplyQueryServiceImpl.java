package com.example.umc8th.reply.service.query;

import com.example.umc8th.article.entity.Article;
import com.example.umc8th.global.apiPayload.code.ReplyErrorCode;
import com.example.umc8th.global.apiPayload.exception.GeneralException;
import com.example.umc8th.reply.entity.Reply;
import com.example.umc8th.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyRepository replyRepository;

    @Override
    public Reply getReply(Long id) {
        return replyRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ReplyErrorCode.REPLY_NOT_FOUND));
    }

    @Override
    public List<Reply> getRepliesByArticleId(Long articleId) {
        return replyRepository.findByArticleId(articleId);
    }
}
