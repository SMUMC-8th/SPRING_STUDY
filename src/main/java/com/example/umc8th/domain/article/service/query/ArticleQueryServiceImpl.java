package com.example.umc8th.domain.article.service.query;

import com.example.umc8th.domain.article.converter.ArticleConverter;
import com.example.umc8th.domain.article.dto.ArticleResponseDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.exception.code.ArticleErrorCode;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {
    private final ArticleRepository articleRepository;

    @Override
    public ArticleResponseDTO.ArticleDTO getArticle(Long articleId) {
        Article artic = isArticleExist(articleId);
        return ArticleConverter.toArticleDTO(artic);
    }

    @Override
    public ArticleResponseDTO.ArticleListDTO getArticles() {
        List<Article> articles = articleRepository.findAll();
        return ArticleConverter.toArticleListDTO(articles);
    }

    @Override
    public Article isArticleExist(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND_404));
    }
}
