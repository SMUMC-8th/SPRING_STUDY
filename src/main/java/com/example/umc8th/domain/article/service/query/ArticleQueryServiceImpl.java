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
    public ArticleResponseDTO.PageArticleDTO getPageArticles(int size, String sort, String rqCursor) {
        Pageable pageable = PageRequest.of(0, size);
        Slice<Article> pageArticle = articleRepository.findAllByOrderByIdDesc(pageable);
        if (sort.equals("likeNum")) {
            pageArticle = articleRepository.findAllByOrderByLikeNumDescIdDesc(pageable);
        }
        // 정렬 기준으로 다시 페이지 로딩
        if (!rqCursor.equals("-1")) {
            if (sort.equals("id")) {
                Long cursor = Long.parseLong(rqCursor);
                pageArticle = articleRepository.findAllNextPageOfId(cursor, pageable);
            }
            if (sort.equals("createdAt")) {
                LocalDateTime cursor = LocalDateTime.parse(rqCursor);
                pageArticle = articleRepository.findAllNextPageOfCreatedAt(cursor, pageable);
            }
            if (sort.equals("likeNum")) {
                pageArticle = articleRepository.findAllNextPageOfLike(rqCursor, pageable);
            }
        }
        return getPageArticleList(pageArticle, sort);
    }

    @Override
    public ArticleResponseDTO.PageArticleDTO serchPageArticles(String query, int size, String sort, String rqCursor) {
        Pageable pageable = PageRequest.of(0, size);
        Slice<Article> pageArticle = articleRepository.findAllByTitleContainingOrderByIdDesc(query, pageable);
        if (sort.equals("likeNum")) {
            pageArticle = articleRepository.findAllByTitleContainingOrderByLikeNumDescIdDesc(query, pageable);
        }
        // 정렬 기준으로 다시 페이지 로딩
        if (!rqCursor.equals("-1")) {
            if (sort.equals("id")) {
                Long cursor = Long.parseLong(rqCursor);
                pageArticle = articleRepository.findAllNextPageOfIdByTitleContaining(cursor, query, pageable);
            }
            if (sort.equals("createdAt")) {
                LocalDateTime cursor = LocalDateTime.parse(rqCursor);
                pageArticle = articleRepository.findAllNextPageOfCreatedAtByTitleContaining(cursor, query, pageable);
            }
            if (sort.equals("likeNum")) {
                pageArticle = articleRepository.findAllNextPageOfLikeByTitleContaining(rqCursor, query, pageable);
            }
        }
        return getPageArticleList(pageArticle, sort);
    }

    private ArticleResponseDTO.PageArticleDTO getPageArticleList(Slice<Article> pageArticle, String sort) {

        // 커서값을 마지막 페이지로
        Article last = pageArticle.getContent().get(pageArticle.getNumberOfElements()-1);
        String cursor = "";
        if (sort.equals("id")){
            cursor = String.valueOf(last.getId());
        }
        if (sort.equals("createdAt")) {
            cursor = String.valueOf(last.getCreatedAt());
        }
        if (sort.equals("likeNum")) {
            cursor = String.format("%05d%010d", last.getLikeNum(), last.getId());
        }
        return ArticleConverter.toPageArticleDTO(
                pageArticle.getContent(),
                cursor,
                pageArticle.hasNext(),
                pageArticle.getSize()
        );
    }
}
