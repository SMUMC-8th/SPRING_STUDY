package com.example.umc8th.code.article.service.query;

import com.example.umc8th.code.article.enums.Active;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return articleRepository.findAllByActive(Active.ACTIVE);
    }

    @Override
    public Article getArticle(Long id) {
        return articleRepository.findByIdAndActive(id, Active.ACTIVE)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
    }

    @Override
    public List<Article> getArticlesByCursor(Long lastArticleId, int size) {
        if (lastArticleId == null) {
            throw new GeneralException(GeneralErrorCode.NOT_FOUND_404);
        }
        Pageable pageable = PageRequest.of(0, size);
        return articleRepository.findByIdLessThanOrderByIdDesc(lastArticleId, pageable);
    }
}
