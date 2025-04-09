package com.example.umc8th.domain.article.service.command.impl;

import com.example.umc8th.domain.article.dto.ArticleRequestDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleErrorCode;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import com.example.umc8th.domain.article.service.command.ArticleCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService {

    private final ArticleRepository articleRepository;

    @Override
    public Article createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        return articleRepository.save(dto.toEntity());
    }

    @Override
    public Article updateArticle(Long id, ArticleRequestDTO.UpdateArticleDTO dto) {
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        article.update(dto.getTitle(), dto.getContent());
        return article;
    }

    @Override
    public Article increaseLike(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        article.increaseLike();
        return article;
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);

    }
}