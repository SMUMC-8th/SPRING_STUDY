package com.example.umc8th.service.query.impl;

import com.example.umc8th.entity.Article;
import com.example.umc8th.entity.Reply;
import com.example.umc8th.repository.ArticleRepository;
import com.example.umc8th.repository.ReplyRepository;
import com.example.umc8th.service.query.ReplyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {
    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    public Reply getReply(Long id){
        return replyRepository.findById(id).get();
    }

    // 페이지네이션 구현으로 삭제 예정
    public List<Reply> getReplies(Long articleId){
        return replyRepository.findAll();
    }

    public Page<Reply> getRepliesByOffset(Long articleId, Integer page, Integer size){
        Article article = articleRepository.findById(articleId).get();
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Reply> reply = replyRepository.findAllByArticleIsOrderByCreatedAtDesc(article, pageable);
        return reply;
    }
}
