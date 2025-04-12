package com.example.umc8th.domain.article.repository;

import com.example.umc8th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "SELECT * FROM article " +
            "WHERE CONCAT(LPAD(like_num, 5, '0'), LPAD(id, 10, '0')) < :cursor " +
            "ORDER BY like_num DESC, id DESC",
            nativeQuery = true)
    Slice<Article> findAllNextPageOfLike (@Param(value = "cursor") String cursor, Pageable pageable);

    @Query("select a from Article a " +
            "where (a.id < :id) " +
            "order by a.id desc")
    Slice<Article> findAllNextPageOfId (@Param(value = "id") Long id, Pageable pageable);

    @Query("select a from Article a " +
            "where (a.createdAt < :createdAt) " +
            "order by a.createdAt desc")
    Slice<Article> findAllNextPageOfCreatedAt (
            @Param(value = "createdAt") LocalDateTime createdAt,
            Pageable pageable
    );

    // 검색 기능이 추가된 쿼리 (검색 기능 빠진 쿼리랑 중복)
    @Query(value = "SELECT * FROM article " +
            "WHERE CONCAT(LPAD(like_num, 5, '0'), LPAD(id, 10, '0')) < :cursor " +
            "AND title LIKE CONCAT('%', :query, '%') " +
            "ORDER BY like_num DESC, id DESC",
            nativeQuery = true)
    Slice<Article> findAllNextPageOfLikeByTitleContaining (
            @Param(value = "cursor") String cursor,
            @Param(value = "query") String query,
            Pageable pageable
    );

    @Query("select a from Article a " +
            "where (a.id < :id) " +
            "AND a.title LIKE CONCAT('%', :query, '%') " +
            "order by a.id desc")
    Slice<Article> findAllNextPageOfIdByTitleContaining (
            @Param(value = "id") Long id,
            @Param(value = "query") String query,
            Pageable pageable
    );

    @Query("select a from Article a " +
            "where (a.createdAt < :createdAt) " +
            "AND a.title LIKE CONCAT('%', :query, '%') " +
            "order by a.createdAt desc")
    Slice<Article> findAllNextPageOfCreatedAtByTitleContaining (
            @Param(value = "createdAt") LocalDateTime createdAt,
            @Param(value = "query") String query,
            Pageable pageable
    );

    Slice<Article> findAllByOrderByIdDesc(Pageable pageable);

    Slice<Article> findAllByOrderByLikeNumDescIdDesc(Pageable pageable);

    Slice<Article> findAllByTitleContainingOrderByIdDesc(String query, Pageable pageable);

    Slice<Article> findAllByTitleContainingOrderByLikeNumDescIdDesc(String query, Pageable pageable);

}
