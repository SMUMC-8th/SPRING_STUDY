package com.example.umc8th.code.article.service.query;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.umc8th.code.article.repository.ArticleRepository;
import com.example.umc8th.code.exception.GeneralErrorCode;
import com.example.umc8th.code.exception.GeneralException;
import com.example.umc8th.code.article.entity.Article;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticle(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
    }
}
