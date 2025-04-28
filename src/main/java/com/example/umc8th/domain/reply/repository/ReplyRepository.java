package com.example.umc8th.domain.reply.repository;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    public List<Reply> findByArticleId(Long articleId);
    // public boolean existsByArticleId(Long articleId);

    Page<Reply> findAllByArticleOrderByCreatedAtDesc(Article article, Pageable pageable);
}
