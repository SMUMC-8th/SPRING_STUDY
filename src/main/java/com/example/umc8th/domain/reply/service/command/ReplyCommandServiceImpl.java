package com.example.umc8th.domain.reply.service.command;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.exception.code.ArticleErrorCode;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import com.example.umc8th.domain.article.service.query.ArticleQueryService;
import com.example.umc8th.domain.reply.converter.ReplyConverter;
import com.example.umc8th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc8th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.domain.reply.repository.ReplyRepository;
import com.example.umc8th.domain.reply.service.query.ReplyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyCommandServiceImpl implements ReplyCommandService {
    private final ReplyRepository replyRepository;
    private final ReplyQueryService replyQueryService;
    private final ArticleRepository articleRepository;

    @Override
    public ReplyResponseDTO.ReplyDTO createReply(ReplyRequestDTO.CreateReplyDTO dto, Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND_404));
        Reply reply = ReplyConverter.toReply(dto,article);
        return ReplyConverter.toReplyDTO(replyRepository.save(reply));
    }

    @Override
    public ReplyResponseDTO.ReplyDTO updateReply(ReplyRequestDTO.UpdateReplyDTO dto, Long articleId, Long replyId) {
        Reply reply = replyQueryService.getReplyInArticle(articleId, replyId);
        reply.updateContent(dto.content());
        return ReplyConverter.toReplyDTO(reply);
    }

    @Override
    public ReplyResponseDTO.DeleteReplyDTO deleteReply(Long articleId, Long replyId) {
        Reply reply = replyQueryService.getReplyInArticle(articleId, replyId);
        replyRepository.delete(reply);
        return ReplyConverter.toDeleteReplyDTO(replyId);
    }

}
