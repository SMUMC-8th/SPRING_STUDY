package com.example.umc8th.domain.article.service.command;

import com.example.umc8th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc8th.domain.article.dto.response.ArticleResDTO;

public interface ArticleCommandService {
    ArticleResDTO.CreateArticleDTO createArticle(ArticleReqDTO.CreateArticleDTO dto);
    ArticleResDTO.UpdateArticleDTO updatePutArticle(ArticleReqDTO.UpdateArticleDTO dto, Long articleId);
    ArticleResDTO.UpdateArticleDTO updatePatchArticle(ArticleReqDTO.UpdateArticleDTO dto, Long articleId);
    ArticleResDTO.DeleteArticleDTO deleteArticle(Long articleId);
}
