package com.example.umc8th.article.service.query;

import com.example.umc8th.article.dto.ArticleRequestDTO;
import com.example.umc8th.article.dto.ArticleResponseDTO;
import com.example.umc8th.article.entity.Article;
import com.example.umc8th.article.repository.ArticleRepository;
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
    public ArticleResponseDTO.articleDTO getArticle(Long articleId) {
        Article artic = articleRepository.findById(articleId).orElseThrow(() ->
                new IllegalArgumentException("Article not found"));
        return ArticleResponseDTO.articleDTO.toDTO(artic);
    }

    @Override
    public List<ArticleResponseDTO.articleDTO> getArticles() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleResponseDTO.articleDTO> articlesList = new ArrayList<>();
        for (Article article : articles) {
            articlesList.add(ArticleResponseDTO.articleDTO.toDTO(article));
        }
        return articlesList;
    }

}
