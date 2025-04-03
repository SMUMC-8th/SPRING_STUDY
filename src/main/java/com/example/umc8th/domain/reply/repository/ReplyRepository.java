package com.example.umc8th.domain.reply.repository;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByArticle(Article article);
}
