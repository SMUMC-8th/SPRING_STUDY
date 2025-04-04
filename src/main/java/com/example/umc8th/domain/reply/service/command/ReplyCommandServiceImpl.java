package com.example.umc8th.domain.reply.service.command;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.exception.code.ArticleErrorCode;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import com.example.umc8th.domain.reply.converter.ReplyConverter;
import com.example.umc8th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc8th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.domain.reply.exception.ReplyErrorCode;
import com.example.umc8th.domain.reply.exception.ReplyException;
import com.example.umc8th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyCommandServiceImpl implements ReplyCommandService {
    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    @Override
    public ReplyResponseDTO.ReplyDTO createReply(ReplyRequestDTO.CreateReplyDTO dto, Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.FORBIDDEN_403));
        Reply reply = ReplyConverter.toReply(dto,article);
        return ReplyConverter.toReplyDTO(replyRepository.save(reply));
    }

    @Override
    public ReplyResponseDTO.ReplyDTO updateReply(ReplyRequestDTO.UpdateReplyDTO dto, Long articleId, Long replyId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND_404));
        Reply reply = replyRepository.findById(replyId).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND_404));
        if (!reply.getArticle().getId().equals(article.getId())) {
            throw new ReplyException(ReplyErrorCode.FORBIDDEN_403);
        }
        reply.updateContent(dto.getContent());
        return ReplyConverter.toReplyDTO(reply);
    }

    @Override
    public ReplyResponseDTO.DeleteReplyDTO deleteReply(Long articleId, Long replyId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND_404));
        Reply reply = replyRepository.findById(replyId).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND_404));
        if (!reply.getArticle().getId().equals(article.getId())) {
            throw new ReplyException(ReplyErrorCode.FORBIDDEN_403);
        }
        replyRepository.deleteById(replyId);
        return ReplyConverter.toDeleteReplyDTO(replyId);
    }


}
