package com.example.umc8th.domain.article.service.query;

import com.example.umc8th.domain.article.dto.ArticleRequestDTO;
import com.example.umc8th.domain.article.dto.ArticleResponseDTO;
import com.example.umc8th.domain.article.entity.Article;

import java.time.LocalDateTime;

public interface ArticleQueryService {
   ArticleResponseDTO.ArticleDTO getArticle(Long articleId);
   Article isArticleExist(Long articleId);
   ArticleResponseDTO.PageArticleDTO getPageArticles(
           int size,
           String sort,
           Long id,
           LocalDateTime createdAt,
           int likeNum
   );
}
