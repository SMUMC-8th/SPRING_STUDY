package com.example.umc8th.domain.article.service.query;

import com.example.umc8th.domain.article.converter.ArticleConverter;
import com.example.umc8th.domain.article.dto.response.ArticleResDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleErrorCode;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.repository.ArticleRepository;
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
    public ArticleResDTO.ArticlePreviewDTO getArticle(Long articleId) {
        // 로직 생각 : articleId로 repository에서 해당 id의 article 조회 -> 조회 한 article을 resDTO로 변환 후 반환
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        return ArticleConverter.toArticlePreviewDTO(article);
    }

    @Override
    public ArticleResDTO.ArticlePreviewListDTO getArticleList() {
        // 로직 생각 : 아직 member가 없으니 repository에서 모든 Entity List 조회 -> Entity List를 DTO 리스트로 변환 후 반환
        List<Article> articles = articleRepository.findAll();
        return ArticleConverter.toArticlePreviewListDTO(articles);
    }
}
