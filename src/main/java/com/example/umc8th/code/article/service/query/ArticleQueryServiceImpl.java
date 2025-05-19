package com.example.umc8th.code.article.service.query;

import com.example.umc8th.code.article.enums.Active;
import com.example.umc8th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc8th.global.apiPayload.code.GeneralException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.umc8th.code.article.repository.ArticleRepository;

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

        Pageable pageable = PageRequest.of(0, size);

        if (lastArticleId == null) {
            //커서값이 없을때 데이터 불러오는거로 바꾸기
            // 처음 요청: 가장 최신글부터 size개 가져오기
            return articleRepository.findAllByOrderByIdDesc(pageable);
        }
        // 이후 요청: lastArticleId보다 작은 ID를 가진 글들 가져오기
        return articleRepository.findByIdLessThanOrderByIdDesc(lastArticleId, pageable);
    }
}
