package com.example.umc8th.repository;

import com.example.umc8th.entity.Article;
import com.example.umc8th.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // 게시글에 댓글이 있는지 확인하는 query. 페이지네이션 구현으로 인해 삭제 예정
    Boolean existsByArticle(Article article);
    // 생성날짜 순서로 댓글 offset 기반 페이지네이션
    Page<Reply> findAllByArticleIsOrderByCreatedAtDesc(Article article, Pageable pageable);
}
