package com.example.umc8th.domain.article.repository;

import com.example.umc8th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // id 기반 Cusor Pagination
    Slice<Article> findAllByIdLessThanOrderByIdDesc(Long id, Pageable pageable);
}