package com.example.umc8th.article.service.query;

import com.example.umc8th.article.dto.ArticleResponseDTO;
import com.example.umc8th.article.entity.Article;
import com.example.umc8th.article.repository.ArticleRepository;
import com.example.umc8th.global.apiPayload.code.ArticleErrorCode;
import com.example.umc8th.global.apiPayload.exception.ArticleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {
    private final ArticleRepository articleRepository;

    @Override
    public ArticleResponseDTO.ArticleDTO getArticle(Long articleId) {
        Article artic = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND_404));
        return ArticleResponseDTO.ArticleDTO.toDTO(artic);
    }

    @Override
    public List<ArticleResponseDTO.ArticleDTO> getArticles() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleResponseDTO.ArticleDTO> articlesList = new ArrayList<>();
        for (Article article : articles) {
            articlesList.add(ArticleResponseDTO.ArticleDTO.toDTO(article));
        }
        return articlesList;
    }

}
