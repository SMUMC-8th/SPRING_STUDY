package com.example.umc8th.domain.reply.service.command;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleErrorCode;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import com.example.umc8th.domain.reply.converter.ReplyConverter;
import com.example.umc8th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.domain.reply.exception.ReplyErrorCode;
import com.example.umc8th.domain.reply.exception.ReplyException;
import com.example.umc8th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto) {
        Article article = articleRepository.findById(dto.getArticleId()).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        return replyRepository.save(ReplyConverter.toReply(dto, article));
    }

    @Override
    public Reply updateReply(Long id, ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new ReplyException(ReplyErrorCode.NOT_FOUND));

        reply.update(dto.getContent());

        return reply;
    }

    @Override
    public Reply deleteReply(Long id) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new ReplyException(ReplyErrorCode.NOT_FOUND));

        replyRepository.delete(reply);

        return reply;
    }

}
