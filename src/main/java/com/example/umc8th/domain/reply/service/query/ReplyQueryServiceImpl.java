package com.example.umc8th.domain.reply.service.query;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.service.query.ArticleQueryService;
import com.example.umc8th.domain.reply.converter.ReplyConverter;
import com.example.umc8th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.domain.reply.exception.ReplyErrorCode;
import com.example.umc8th.domain.reply.exception.ReplyException;
import com.example.umc8th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyQueryServiceImpl implements ReplyQueryService {
    private final ArticleQueryService articleQueryService;
    private final ReplyRepository replyRepository;

    @Override
    public ReplyResponseDTO.ReplyListDTO getReplyList(Long articleId) {
        Article article = articleQueryService.isArticleExist(articleId);
        List<Reply> replies = replyRepository.findAllByArticle(article);
        return ReplyConverter.toReplyListDTO(replies);
    }

    @Override
    public Reply isReplyExistInArticle(Long articleId, Long replyId) {
        Article article = articleQueryService.isArticleExist(articleId);
        Reply reply = replyRepository.findReplyByArticleAndId(article, replyId).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND_404));
        reply.updateArticle(article);
        return reply;
    }
}
