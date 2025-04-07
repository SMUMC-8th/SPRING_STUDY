package com.example.umc8th.domain.reply.service.command;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import com.example.umc8th.domain.reply.converter.ReplyConverter;
import com.example.umc8th.domain.reply.dto.response.ReplyResDTO;
import com.example.umc8th.global.apiPayload.error.ArticleErrorCode;
import com.example.umc8th.global.apiPayload.error.GeneralErrorCode;
import com.example.umc8th.global.apiPayload.error.exception.GeneralException;
import com.example.umc8th.domain.reply.dto.reqeust.ReplyReqDTO;
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
    public ReplyResDTO.CreateReplyDTO createReply(ReplyReqDTO.CreateReplyDTO dto, Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new GeneralException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        Reply reply = ReplyConverter.toReply(dto, article);
        replyRepository.save(reply);

        return ReplyConverter.toCreateReplyDTO(reply);
    }

    @Override
    public ReplyResDTO.UpdateReplyDTO updateReply(ReplyReqDTO.UpdateReplyDTO dto, Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));

        reply.update(dto.content());

        return ReplyConverter.toUpdateReplyDTO(reply);
    }

    @Override
    public ReplyResDTO.DeleteReplyDTO deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);

        return ReplyConverter.toDeleteReplyDTO(replyId);
    }
}
