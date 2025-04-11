package com.example.umc8th.service.impl;

import com.example.umc8th.converter.ReplyConverter;
import com.example.umc8th.dto.ReplyRequestDTO;
import com.example.umc8th.entity.Article;
import com.example.umc8th.entity.Reply;
import com.example.umc8th.exception.ArticleErrorCode;
import com.example.umc8th.exception.ArticleException;
import com.example.umc8th.repository.ArticleRepository;
import com.example.umc8th.repository.ReplyRepository;
import com.example.umc8th.service.ReplyCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService {
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Reply createArticle(ReplyRequestDTO.CreateReplyDTO dto){
        Article article = articleRepository.findById(dto.getArticleId()).orElseThrow(()->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        return replyRepository.save(ReplyConverter.toReply(dto, article));
    }
}
