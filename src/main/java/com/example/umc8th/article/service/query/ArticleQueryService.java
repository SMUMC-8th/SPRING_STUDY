package com.example.umc8th.article.service.query;

import com.example.umc8th.article.dto.ArticleResponseDTO;

import java.util.List;

public interface ArticleQueryService {
   ArticleResponseDTO.ArticleDTO getArticle(Long articleId);
   List<ArticleResponseDTO.ArticleDTO> getArticles();
}
