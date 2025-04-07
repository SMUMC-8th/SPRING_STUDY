package com.example.umc8th.domain.article.service.query;

import com.example.umc8th.domain.article.dto.response.ArticleResDTO;
import com.example.umc8th.domain.article.entity.Article;

import java.util.List;

public interface ArticleQueryService {
    ArticleResDTO.ArticlePreviewDTO getArticle(Long id);
    ArticleResDTO.ArticlePreviewListDTO getArticles();
}