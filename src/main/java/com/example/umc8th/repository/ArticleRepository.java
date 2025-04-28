package com.example.umc8th.repository;

import com.example.umc8th.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // 게시글 cursor 기반 페이지네이션
    Slice<Article> findAllByIdLessThanOrderByIdDesc(Long id, Pageable pageable);
}