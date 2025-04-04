package com.example.umc8th.domain.article.service.command;

import com.example.umc8th.domain.article.converter.ArticleConverter;
import com.example.umc8th.domain.article.dto.ArticleRequestDTO;
import com.example.umc8th.domain.article.dto.ArticleResponseDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.exception.code.ArticleErrorCode;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService {
    private final ArticleRepository articleRepository;

    @Override
    public ArticleResponseDTO.ArticleDTO createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        Article article = ArticleConverter.toArticle(dto);
        return ArticleConverter.toArticleDTO(articleRepository.save(article));
    }

    @Override
    @Transactional
    public ArticleResponseDTO.ArticleDTO updateArticleAll(ArticleRequestDTO.UpdateArticleDTO dto, Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND_404));
        article.updateAll(dto.getContent(),dto.getTitle());
        return ArticleConverter.toArticleDTO(article);
    }

    @Override
    @Transactional
    public ArticleResponseDTO.ArticleDTO updateArticleContent(
            ArticleRequestDTO.UpdateArticleDTO dto,
            Long articleId
    ) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND_404));
        article.updateContent(dto.getContent());
        return ArticleConverter.toArticleDTO(article);
    }

    @Override
    @Transactional
    public ArticleResponseDTO.ArticleDTO updateArticleTitle(
            ArticleRequestDTO.UpdateArticleDTO dto,
            Long articleId
    ) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND_404));
        article.updateTitle(dto.getTitle());
        return ArticleConverter.toArticleDTO(article);
    }

}
