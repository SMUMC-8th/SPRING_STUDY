package com.example.umc8th.domain.reply.service.query;

import com.example.umc8th.domain.reply.converter.ReplyConverter;
import com.example.umc8th.domain.reply.dto.response.ReplyResDTO;
import com.example.umc8th.global.apiPayload.error.ReplyErrorCode;
import com.example.umc8th.global.apiPayload.error.exception.GeneralException;
import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyRepository replyRepository;

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
}
