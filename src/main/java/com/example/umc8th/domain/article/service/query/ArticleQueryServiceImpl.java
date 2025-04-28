package com.example.umc8th.domain.article.service.query;

import com.example.umc8th.domain.article.converter.ArticleConverter;
import com.example.umc8th.domain.article.dto.response.ArticleResDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import com.example.umc8th.global.apiPayload.error.ArticleErrorCode;
import com.example.umc8th.global.apiPayload.error.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
// Query는 읽기만 하니 ReadOnly로 작성
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public ArticleResDTO.ArticlePreviewListDTO getArticles() {

        return ArticleConverter.toArticlePreviewListDTO(articleRepository.findAll());
    }

    // findById -> Optional<Article> 반환
    // .get() -> 해당 ID 없을 시 NoSuchElementException 발생
    @Override
    public ArticleResDTO.ArticlePreviewDTO getArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        return ArticleConverter.toArticlePreviewDTO(article);
    }

    @Override
    public ArticleResDTO.ArticlePreviewListDTO getArticlesByCursorPagination(Long cursor, int size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "id"));

        Slice<Article> articles = articleRepository.findAllByIdLessThanOrderByIdDesc(cursor, pageable);

        List<ArticleResDTO.ArticlePreviewDTO> dtoList = articles.stream()
                .map(ArticleConverter::toArticlePreviewDTO)
                .collect(Collectors.toList());

        boolean hasNext = dtoList.size() == size;
        Long nextCursor = hasNext ? dtoList.get(dtoList.size() - 1).id() : null;

        return ArticleConverter.toArticlePreviewListCursorPaginationDTO(articles.getContent(), hasNext, nextCursor);
    }
}