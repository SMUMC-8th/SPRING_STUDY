package com.example.umc8th.domain.article.repository;

import com.example.umc8th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
// JpaRepository의 첫 번째는 해당 Repository가 사용할 클래스(엔티티)가 들어가야합니다.
// 두 번째는 id의 자료형을 적어줍니다.

    // ID
    Slice<Article> findAllByIdLessThanOrderByIdDesc(Long id, Pageable pageable);
    Slice<Article> findAllByOrderByIdDesc(Pageable pageable);

    // Like
    @Query(value = "SELECT a.* FROM article a " +
            "JOIN (SELECT a2.id, CONCAT(LPAD(a2.like_num, 10, '0'), LPAD(a2.id, 10, '0')) as cursorValue FROM article a2) as cursorTable ON a.id = cursorTable.id " +
            "WHERE cursorValue < (SELECT CONCAT(LPAD(a3.like_num, 10, '0'), LPAD(a3.id, 10, '0')) as cursorValue FROM article a3 WHERE a3.id = :articleId) " +
            "ORDER BY cursorTable.cursorValue DESC",
            nativeQuery = true)
    Slice<Article> findAllByOrderByLikeWithCursor(@Param("articleId") Long cursor, Pageable pageable);

    @Query("SELECT a FROM Article a WHERE a.likeNum < :likeNum OR (a.likeNum = :likeNum AND a.id < :id) ORDER BY a.likeNum DESC, a.id DESC")
    Slice<Article> findAllByOrderByLikeWithCursorV2(@Param("articleId") Long cursor, Pageable pageable);
    Slice<Article> findAllByOrderByLikeNumDescIdDesc(Pageable pageable);

    List<Article> findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);
}