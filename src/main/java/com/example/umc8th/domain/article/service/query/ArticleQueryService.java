package com.example.umc8th.domain.article.service.query;

import com.example.umc8th.domain.article.dto.response.ArticleResDTO;

public interface ArticleQueryService {

    ArticleResDTO.ArticlePreviewDTO getArticle(Long articleId);
    ArticleResDTO.ArticlePreviewListDTO getArticleList();
    ArticleResDTO.ArticlePreviewListDTO getArticlesByCursor(Long cursor, int size, String sortBy);
    ArticleResDTO.ArticlePreviewListDTO searchArticlesByTitle(String keyword, int page, int size);
}
