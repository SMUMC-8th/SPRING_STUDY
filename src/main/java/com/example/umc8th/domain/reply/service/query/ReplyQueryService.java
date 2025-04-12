package com.example.umc8th.domain.reply.service.query;

import com.example.umc8th.domain.reply.dto.response.ReplyResDTO;
import com.example.umc8th.domain.reply.entity.Reply;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReplyQueryService {
    ReplyResDTO.PreviewReplyDTO getReply(Long id);
    ReplyResDTO.PreviewListReplyDTO getRepliesByArticleId(Long articleId);
    ReplyResDTO.PreviewListReplyDTO getRepliesOffsetPagination(Long articleId, int page, int size);
}
