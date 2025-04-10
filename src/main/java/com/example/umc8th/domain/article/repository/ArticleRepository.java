package com.example.umc8th.domain.article.repository;

import com.example.umc8th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select a from Article a " +
            "where (a.likeNum < :like) or (a.likeNum = :like and a.id < :id)" +
            "order by a.likeNum desc, a.id desc")
    Slice<Article> findAllNextPageOfLike (
            @Param(value = "like") int like,
            @Param(value = "id") Long id,
            Pageable pageable
    );

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

    Slice<Article> findAllByOrderByLikeNumDesc(Pageable pageable);
}
