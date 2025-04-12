package com.example.umc8th.domain.reply.service.query;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import com.example.umc8th.domain.reply.converter.ReplyConverter;
import com.example.umc8th.domain.reply.dto.response.ReplyResDTO;
import com.example.umc8th.global.apiPayload.error.ArticleErrorCode;
import com.example.umc8th.global.apiPayload.error.ReplyErrorCode;
import com.example.umc8th.global.apiPayload.error.exception.GeneralException;
import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    @Override
    public ReplyResDTO.PreviewReplyDTO getReply(Long id) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ReplyErrorCode.REPLY_NOT_FOUND));

        return ReplyConverter.toPreviewReplyDTO(reply);
    }

    @Override
    public ReplyResDTO.PreviewListReplyDTO getRepliesByArticleId(Long articleId) {
        return ReplyConverter.toPreviewListReplyDTO(replyRepository.findByArticleId(articleId));
    }


    @Override
    public ReplyResDTO.PreviewListReplyDTO getRepliesOffsetPagination(Long articleId, int page, int size) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new GeneralException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        Pageable pageable = PageRequest.of(page, size);
        Page<Reply> replyPage = replyRepository.findAllByArticleOrderByCreatedAtDesc(article, pageable);

        return ReplyConverter.toPreviewListReplyOffsetPaginationDTO(replyPage);
    }
}
