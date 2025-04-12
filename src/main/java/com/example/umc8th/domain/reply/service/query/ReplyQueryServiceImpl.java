package com.example.umc8th.domain.reply.service.query;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleErrorCode;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.domain.reply.exception.ReplyErrorCode;
import com.example.umc8th.domain.reply.exception.ReplyException;
import com.example.umc8th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService{

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Reply getReply(Long id) {
        return replyRepository.findById(id).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND));
    }

    @Override
    public List<Reply> getReplies(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        return replyRepository.findAllByArticleId(article);
    }

    @Override
    public Boolean existsReply(Long articleId) {
        return replyRepository.existsById(articleId);
    }

    @Override
    public Page<Reply> getRepliesPageable(Long articleId,  int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return replyRepository.findAllByArticleId(articleId, pageable);
    }
}
