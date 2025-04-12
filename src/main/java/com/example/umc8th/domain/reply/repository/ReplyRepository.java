package com.example.umc8th.domain.reply.repository;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByArticleId(Article article);
    List<Reply> findAllByArticleIsOrderByCreatedAtDesc(Article article);

    // 게시글에 댓글이 있는지 확인하는 Query 생성
    boolean existsByArticleId(Long articleId);

    Page<Reply> findAllByArticleId(Long articleId, Pageable pageable);
}
