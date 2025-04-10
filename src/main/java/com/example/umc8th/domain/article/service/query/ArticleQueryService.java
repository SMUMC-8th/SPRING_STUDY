package com.example.umc8th.domain.article.service.query;

import com.example.umc8th.domain.article.dto.ArticleResponseDTO;
import com.example.umc8th.domain.article.entity.Article;

public interface ArticleQueryService {
   ArticleResponseDTO.ArticleDTO getArticle(Long articleId);
   Article isArticleExist(Long articleId);
   ArticleResponseDTO.PageArticleDTO getPageArticles(int size, String sort, String cursor);
   ArticleResponseDTO.PageArticleDTO serchPageArticles(String query, int size, String sort, String cursor);
}
