package com.example.umc8th.domain.article.service.query;

import com.example.umc8th.domain.article.converter.ArticleConverter;
import com.example.umc8th.domain.article.dto.ArticleResponseDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.exception.code.ArticleErrorCode;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {
    private final ArticleRepository articleRepository;

    @Override
    public ArticleResponseDTO.ArticleDTO getArticle(Long articleId) {
        Article artic = isArticleExist(articleId);
        return ArticleConverter.toArticleDTO(artic);
    }

    @Override
    public Article isArticleExist(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND_404));
    }

    @Override
    public ArticleResponseDTO.PageArticleDTO getPageArticles(
            int size,
            String sort,
            Long id,
            LocalDateTime createdAt,
            int likeNum
    ) {
        // 초기 설정
        Pageable pageable = PageRequest.of(0, size);
        Slice<Article> pageArticle = articleRepository.findAllByOrderByIdDesc(pageable);
        if (sort.equals("likeNum")) {
            pageArticle = articleRepository.findAllByOrderByLikeNumDesc(pageable);
        }
        // 정렬 기준으로 다시 페이지 로딩
        if (sort.equals("id") && id != -1) {
            pageArticle = articleRepository.findAllNextPageOfId(id, pageable);
        }
        if (sort.equals("createdAt") && createdAt != null) {
            pageArticle = articleRepository.findAllNextPageOfCreatedAt(createdAt, pageable);
        }
        if (sort.equals("likeNum") && likeNum != -1) {
            pageArticle = articleRepository.findAllNextPageOfLike(likeNum, id, pageable);
        }
        // 커서값을 마지막 페이지로
        Article last = pageArticle.getContent().get(pageArticle.getNumberOfElements()-1);
        ArticleResponseDTO.ResCursor resCursor = ArticleConverter.toResCursor(
                last.getId(),
                last.getLikeNum(),
                last.getCreatedAt()
        );
        return ArticleConverter.toPageArticleDTO(
                pageArticle.getContent(),
                resCursor,
                pageArticle.hasNext(),
                pageArticle.getSize()
        );
    }
}
