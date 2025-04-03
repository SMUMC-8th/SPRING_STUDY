package com.example.umc8th.article.service.query;

import com.example.umc8th.article.dto.ArticleResponseDTO;

public interface ArticleQueryService {
   ArticleResponseDTO.ArticleDTO getArticle(Long articleId);
   ArticleResponseDTO.ArticleListDTO getArticles();
}
