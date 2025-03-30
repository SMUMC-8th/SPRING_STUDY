package com.example.umc8th.article.service.query;

import com.example.umc8th.article.dto.ArticleRequestDTO;
import com.example.umc8th.article.dto.ArticleResponseDTO;
import com.example.umc8th.article.entity.Article;

import java.util.List;

public interface ArticleQueryService {
   ArticleResponseDTO.articleDTO getArticle(Long articleId);
   List<ArticleResponseDTO.articleDTO> getArticles();
}
