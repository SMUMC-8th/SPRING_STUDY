package com.example.umc8th.service.query;

import com.example.umc8th.entity.Article;
import com.example.umc8th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc8th.global.apiPayload.code.GeneralException;
import com.example.umc8th.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public List<Article> getArticles() {
        // 구현, 힌트: findAll()
        return articleRepository.findAll();
    }

    @Override
    public Article getArticle(Long id) {
        // 구현, 힌트: findById(Long id)
        // findById의 결과로 Optional 형태가 나올 예정인데 1주차 워크북의 구현된 Error code를 참고하여 ArticleErrorCode를 작성해보시고 직접 에러를 발생시키셔도 좋고 아니면 일단 .get()을 사용하시고 제가 세미나에서 알려드릴게요
        Optional<Article> findArticle = articleRepository.findById(id);
        if(findArticle.isPresent()) {
            return findArticle.get();
        }
//        return new GeneralException(GeneralErrorCode.NOT_FOUND_404);
        return articleRepository.findById(id)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
    }
}
