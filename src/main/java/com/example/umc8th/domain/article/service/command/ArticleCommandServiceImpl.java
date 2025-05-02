package com.example.umc8th.domain.article.service.command;

import com.example.umc8th.domain.article.dto.ArticleRequestDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleErrorCode;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.umc8th.domain.article.exception.ArticleErrorCode.NOT_FOUND;

// Service로 사용하겠다고 명시 (빈 주입)
@Service
// Transactional을 사용하겠다고 명시. 모든 메소드가 하나의 Transaction 단위로 동작, 단일 메소드에도 선언 가능
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