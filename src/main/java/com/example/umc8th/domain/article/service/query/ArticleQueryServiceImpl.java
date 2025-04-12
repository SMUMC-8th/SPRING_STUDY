package com.example.umc8th.domain.article.service.query;

import com.example.umc8th.domain.article.converter.ArticleConverter;
import com.example.umc8th.domain.article.dto.response.ArticleResDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleErrorCode;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    @Override
    public ArticleResDTO.ArticlePreviewListDTO getArticlesByCursor(Long cursor, int offset, String sort) {
        Pageable pageable = PageRequest.of(0, offset);

        // 정렬 기준에 따른 커서 페이지네이션 처리
        Slice<Article> articles = switch (sort) {
            case "date" -> getArticlesByCreatedAtCursor(cursor, pageable);
            case "like" -> getArticlesByLikeNumCursor(cursor, pageable);
            default -> getArticlesByIdCursor(cursor, pageable);
        };

        // 다음 커서 계산
        Long nextCursor = calculateNextCursor(articles, sort);

        // 변환하여 반환
        return ArticleConverter.toArticlePreviewListDTOWithCursor(
                articles.getContent(),
                articles.hasNext(),
                nextCursor
        );
    }

    @Override
    public ArticleResDTO.ArticlePreviewListDTO searchArticlesByTitle(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Article> articlePage = articleRepository.findAllByTitleContainingOrderByCreatedAtDesc(keyword, pageable);

        List<ArticleResDTO.ArticlePreviewDTO> articleDtoList = articlePage.getContent()
                .stream()
                .map(ArticleConverter::toArticlePreviewDTO)
                .toList();

        return ArticleResDTO.ArticlePreviewListDTO.builder()
                .articlePreviewDtoList(articleDtoList)
                .build();
    }

    // ID 기준 커서 페이지네이션
    private Slice<Article> getArticlesByIdCursor(Long cursor, Pageable pageable) {
        Long cursorId = cursor != null ? cursor : Long.MAX_VALUE;
        return articleRepository.findAllByIdLessThanOrderByIdDesc(cursorId, pageable);
    }

    // 생성 날짜 기준 커서 페이지네이션
    private Slice<Article> getArticlesByCreatedAtCursor(Long cursor, Pageable pageable) {
        LocalDateTime cursorCreatedAt = cursor != null
                ? LocalDateTime.ofInstant(Instant.ofEpochMilli(cursor), ZoneId.systemDefault())
                : LocalDateTime.now();
        return articleRepository.findAllByCreatedAtLessThanOrderByCreatedAtDesc(cursorCreatedAt, pageable);
    }

    // 좋아요 수 기준 커서 페이지네이션
    private Slice<Article> getArticlesByLikeNumCursor(Long cursor, Pageable pageable) {
        int likeNum = cursor != null ? (int) (cursor >> 32) : Integer.MAX_VALUE;
        Long id = cursor != null ? cursor & 0xFFFFFFFFL : Long.MAX_VALUE;
        return articleRepository.findByLikeNumAndIdCursor(likeNum, id, pageable);
    }

    // 다음 커서 계산
    private Long calculateNextCursor(Slice<Article> articles, String sort) {
        if (!articles.hasNext()) {
            return null; // 다음 페이지가 없으면 null 반환
        }

        Article lastArticle = articles.getContent().get(articles.getNumberOfElements() - 1);

        // 정렬 기준에 따라 커서 값 계산
        return switch (sort) {
            case "date" -> lastArticle.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            case "like" -> ((long) lastArticle.getLikeNum() << 32) | lastArticle.getId();
            default -> lastArticle.getId();
        };
    }
}
