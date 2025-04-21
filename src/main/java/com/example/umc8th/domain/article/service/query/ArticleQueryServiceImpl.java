package com.example.umc8th.domain.article.service.query;


import com.example.umc8th.domain.article.dto.ArticleResponseDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleErrorCode;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
// Query는 읽기만 하니 ReadOnly로 작성
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;


    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public ArticleResponseDTO.PageArticleDTO getPageArticles(int size, String sort, String cursor) {
        return null;
    }

    @Override
    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        // findById의 결과로 Optional 형태가 나올 예정인데 1주차 워크북의 구현된 Error code를 참고하여
        // ArticleErrorCode를 작성해보시고 직접 에러를 발생시키셔도 좋고 아니면 일단 .get()을 사용하시고 제가 세미나에서 알려드릴게요


    }

    @Override
    public  ArticleResponseDTO.PageArticleDTO getPageArticlesCursorById(int size, Long cursor) {
        Pageable pageable = PageRequest.of(0, size);
        Slice<Article> pageArticle = null;
        pageArticle = articleRepository.findAllNextPageOfId(cursor, pageable);

    }
}
