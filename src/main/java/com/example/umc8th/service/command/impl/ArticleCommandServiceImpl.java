package com.example.umc8th.service.command.impl;

import com.example.umc8th.dto.ArticleRequestDTO;
import com.example.umc8th.entity.Article;
import com.example.umc8th.exception.ArticleErrorCode;
import com.example.umc8th.exception.ArticleException;
import com.example.umc8th.repository.ArticleRepository;
import com.example.umc8th.service.command.ArticleCommandService;
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
    public Article updateArticle(Long id, ArticleRequestDTO.UpdateArticleDTO dto){
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        article.update(dto.getTitle(), dto.getContent());
        return article;
    }

    @Override
    public Article updateArticleContent(Long id, ArticleRequestDTO.UpdateArticleContentDTO dto) {
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        article.update(article.getTitle(), dto.getContent());
        return article;
    }


    @Override
    public Long deleteArticle(Long id){
        articleRepository.deleteById(id);
        return id;
    }
}
