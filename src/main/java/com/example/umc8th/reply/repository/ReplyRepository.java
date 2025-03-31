package com.example.umc8th.reply.repository;

import com.example.umc8th.article.entity.Article;
import com.example.umc8th.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    public List<Reply> findByArticleId(Long articleId);

}
