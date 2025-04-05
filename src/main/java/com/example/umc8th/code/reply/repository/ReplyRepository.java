package com.example.umc8th.code.reply.repository;

import com.example.umc8th.code.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.umc8th.code.reply.entity.Reply;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
    List<Reply> findAllByArticle(Article article);
}