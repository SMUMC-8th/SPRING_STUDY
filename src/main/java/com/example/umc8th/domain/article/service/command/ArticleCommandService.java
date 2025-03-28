package com.example.umc8th.domain.article.service.command;

import com.example.umc8th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc8th.domain.article.dto.response.ArticleResDTO;

public interface ArticleCommandService {

    ArticleResDTO.CreateArticleResDTO createArticle(ArticleReqDTO.CreateArticleReqDTO reqDTO);
}
