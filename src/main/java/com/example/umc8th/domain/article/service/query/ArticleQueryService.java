package com.example.umc8th.domain.article.service.query;

import com.example.umc8th.domain.article.dto.ArticleResponseDTO;
import com.example.umc8th.domain.article.entity.Article;

public interface ArticleQueryService {
   ArticleResponseDTO.ArticleDTO getArticle(Long articleId);
   ArticleResponseDTO.ArticleListDTO getArticles();
   Article isArticleExist(Long articleId);
}
