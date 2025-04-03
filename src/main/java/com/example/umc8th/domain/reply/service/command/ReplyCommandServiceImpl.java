package com.example.umc8th.domain.reply.service.command;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import com.example.umc8th.global.apiPayload.code.ArticleErrorCode;
import com.example.umc8th.global.apiPayload.exception.GeneralException;
import com.example.umc8th.domain.reply.dto.reqeust.ReplyRequestDTO;
import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto, Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new GeneralException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        return replyRepository.save(
                Reply.builder()
                        .content(dto.getContent())
                        .article(article)
                        .build()
        );
    }
}
