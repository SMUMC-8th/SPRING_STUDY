package com.example.umc8th.article.service.query;

import com.example.umc8th.article.entity.Article;
import com.example.umc8th.article.repository.ArticleRepository;
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
    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

}
