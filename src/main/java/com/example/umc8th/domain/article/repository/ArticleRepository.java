package com.example.umc8th.domain.article.repository;

import com.example.umc8th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
// JpaRepository의 첫 번째는 해당 Repository가 사용할 클래스(엔티티)가 들어가야합니다.
// 두 번째는 id의 자료형을 적어줍니다.

    // id 기반 커서 페이징
    @Query("select a from Article a " +
            "where (a.id < :id) " +
            "order by a.id desc")
    Slice<Article> findAllNextPageOfId (@Param(value = "id") Long id, Pageable pageable);
}