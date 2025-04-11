package com.example.umc8th.code.article.repository;

import com.example.umc8th.code.article.enums.Active;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.umc8th.code.article.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
// JpaRepository의 첫 번째는 해당 Repository가 사용할 클래스(엔티티)가 들어가야합니다.
// 두 번째는 id의 자료형을 적어줍니다.
    Optional<Article> findByIdAndActive(Long id, Active active);
    List<Article> findAllByActive(Active active);

//게시글 cursor기반 페이지네이션
    List<Article> findByIdLessThanOrderByIdDesc(Long lastArticleId, Pageable pageable);    // 처음 조회할 땐 lastArticleId 없이 조회
}