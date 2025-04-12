package com.example.umc8th.domain.reply.repository;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByArticle(Article article);

    Optional<Reply> findReplyByArticleAndId(Article article, Long id);

    Page<Reply> findAllByArticleIdOrderByCreatedAtDesc(Long articleId, Pageable pageable);
}
