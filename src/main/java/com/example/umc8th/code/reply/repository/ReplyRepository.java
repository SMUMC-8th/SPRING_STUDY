package com.example.umc8th.code.reply.repository;

import com.example.umc8th.code.article.entity.Article;
import com.example.umc8th.code.article.enums.Active;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.umc8th.code.reply.entity.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
    List<Reply> findAllByArticle(Article article);
//    Optional<Reply> findByActiveArticle(Article article, Active active);

}