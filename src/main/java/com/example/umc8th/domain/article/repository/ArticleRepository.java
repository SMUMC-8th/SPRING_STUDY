package com.example.umc8th.domain.article.repository;

import com.example.umc8th.domain.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // id 기준 커서 페이지네이션
    Slice<Article> findAllByIdLessThanOrderByIdDesc(Long id, Pageable pageable);

    // 생성 날짜 기준 커서 페이지네이션
    Slice<Article> findAllByCreatedAtLessThanOrderByCreatedAtDesc(LocalDateTime createdAt, Pageable pageable);

    // 좋아요 수 기준 커서 페이지네이션
    @Query("SELECT a FROM Article a WHERE a.likeNum < :likeNum OR (a.likeNum = :likeNum AND a.id < :id) ORDER BY a.likeNum DESC, a.id DESC")
    Slice<Article> findByLikeNumAndIdCursor(@Param("likeNum") int likeNum, @Param("id") Long id, Pageable pageable);

    // 제목에 특정 문자열을 포함하는 게시글 검색
    Page<Article> findAllByTitleContainingOrderByCreatedAtDesc(String keyword, Pageable pageable);
}
