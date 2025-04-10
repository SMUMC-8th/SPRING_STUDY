package com.example.umc8th.domain.article.service.query.impl;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.enums.ArticleSearchQuery;
import com.example.umc8th.domain.article.exception.ArticleErrorCode;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import com.example.umc8th.domain.article.service.query.ArticleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public Slice<Article> getArticles(String query, Long cursor, Integer offset) {
        Pageable pageable = PageRequest.of(0, offset);
        if (query.equals(ArticleSearchQuery.ID.name())) {
            if (cursor.equals(0L)) {
                return articleRepository.findAllByOrderByIdDesc(pageable);
            }
            return articleRepository.findAllByIdLessThanOrderByIdDesc(cursor, pageable);
        }
        else if (query.equals(ArticleSearchQuery.LIKE.name())) {
            if (cursor.equals(0L)) {
                return articleRepository.findAllByOrderByLikeNumDescIdDesc(pageable);
            }
            return articleRepository.findAllByOrderByLikeWithCursor(cursor, pageable);
        }
        else {
            throw new ArticleException(ArticleErrorCode.UNSUPPORTED_QUERY);
        }

    }

    @Override
    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
    }
}
