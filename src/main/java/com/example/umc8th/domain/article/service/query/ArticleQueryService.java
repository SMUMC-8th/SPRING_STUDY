package com.example.umc8th.domain.article.service.query;

import com.example.umc8th.domain.article.dto.response.ArticleResDTO;

public interface ArticleQueryService {

    ArticleResDTO.ArticlePreviewDTO getArticle(Long articleId);
    ArticleResDTO.ArticlePreviewListDTO getArticleList();
}
