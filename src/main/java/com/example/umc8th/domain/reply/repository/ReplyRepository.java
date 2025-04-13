package com.example.umc8th.domain.reply.repository;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByArticle(Article article);

    // 특정 게시글에 댓글이 있는지 확인
    boolean existsByArticleId(Long articleId);

    // Offset 기반 페이지네이션 - 생성 날짜 내림차순(최신순)
    Page<Reply> findAllByArticleOrderByCreatedAtDesc(Article article, Pageable pageable);
}
