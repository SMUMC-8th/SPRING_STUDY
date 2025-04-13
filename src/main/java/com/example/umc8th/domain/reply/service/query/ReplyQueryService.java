package com.example.umc8th.domain.reply.service.query;

import com.example.umc8th.domain.reply.dto.response.ReplyResDTO;

public interface ReplyQueryService {

    ReplyResDTO.ReplyPreviewListDTO getRepliesByArticle(Long articleId);
    ReplyResDTO.ReplyPreviewListDTO getRepliesByArticlePaginated(Long articleId, int page, int size);
}
