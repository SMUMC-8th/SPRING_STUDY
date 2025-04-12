package com.example.umc8th.domain.reply.service.query;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.exception.code.ArticleErrorCode;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import com.example.umc8th.domain.article.service.query.ArticleQueryService;
import com.example.umc8th.domain.reply.converter.ReplyConverter;
import com.example.umc8th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.domain.reply.exception.ReplyErrorCode;
import com.example.umc8th.domain.reply.exception.ReplyException;
import com.example.umc8th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyQueryServiceImpl implements ReplyQueryService {
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Reply getReplyInArticle(Long articleId, Long replyId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND_404));
        Reply reply = replyRepository.findReplyByArticleAndId(article, replyId).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND_404));
        reply.updateArticle(article);
        return reply;
    }

    @Override
    public ReplyResponseDTO.PageReplyDTO getReplyList(Long articleId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Reply> replyPage = replyRepository.findAllByArticleIdOrderByCreatedAtDesc(articleId, pageable);
        return ReplyConverter.toPageReplyDTO(
                replyPage.getContent(),
                replyPage.getNumber()+1,
                replyPage.getTotalPages());
    }
}
