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

    Slice<Article> findAllByOrderByIdDesc(Pageable pageable);

    Slice<Article> findAllByOrderByLikeNumDescIdDesc(Pageable pageable);
}
