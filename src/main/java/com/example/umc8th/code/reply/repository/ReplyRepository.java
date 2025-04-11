package com.example.umc8th.code.reply.repository;

import com.example.umc8th.code.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.umc8th.code.reply.entity.Reply;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByArticle(Article article);

    //게시글에 해당되는 댓글을 날짜 내림차순해서 전부 가져옴
    List<Reply> findAllByArticleIsOrderByCreatedAtDesc(Article article);

    //댓글을 offset기반 페이지네이션 - 생성날짜순서로
    Page<Reply> findByArticleId(Long articleId, Pageable pageable);
}
