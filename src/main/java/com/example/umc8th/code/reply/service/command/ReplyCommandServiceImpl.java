package com.example.umc8th.code.reply.service.command;

import com.example.umc8th.code.article.entity.Article;
import com.example.umc8th.code.article.enums.Active;
import com.example.umc8th.code.article.repository.ArticleRepository;
import com.example.umc8th.code.exception.GeneralErrorCode;
import com.example.umc8th.code.exception.GeneralException;
import com.example.umc8th.code.reply.converter.ReplyConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.umc8th.code.reply.dto.ReplyRequestDTO;
import com.example.umc8th.code.reply.entity.Reply;
import com.example.umc8th.code.reply.repository.ReplyRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    //아티클이 있으면 댓글 생성
    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto, Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
        return replyRepository.save(
                ReplyConverter.toEntity(dto, article));
    }

    // 기존 댓글을 찾기
    @Override
    public Reply findActiveReply(Long articleId, Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));

        // 기존 댓글이 속한 게시글이 요청한 articleId와 일치하고, ACTIVE 상태인지 확인
        Article article = reply.getArticle();
        if (!article.getId().equals(articleId) || article.getActive() != Active.ACTIVE) {
            throw new GeneralException(GeneralErrorCode.NOT_FOUND_404);
        }

        return reply;
    }

    //기존 댓글이 있으면 수정
    @Override
    public Reply saveAndUpdate(Long articleId, ReplyRequestDTO.UpdateReplyDTO dto) {

        Reply reply = findActiveReply(articleId, dto.getReplyId());

        reply.update(dto.getContent());
        return reply;
    }

    //기존 댓글이 있으면 삭제
    @Override
    public void deleteReply(Long articleId, Long replyId) {
        Reply reply = findActiveReply(articleId, replyId);
        reply.softDelete();
    }
}



