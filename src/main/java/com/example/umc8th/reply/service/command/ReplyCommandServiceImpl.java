package com.example.umc8th.reply.service.command;

import com.example.umc8th.article.entity.Article;
import com.example.umc8th.article.repository.ArticleRepository;
import com.example.umc8th.reply.dto.ReplyRequestDTO;
import com.example.umc8th.reply.dto.ReplyResponseDTO;
import com.example.umc8th.reply.entity.Reply;
import com.example.umc8th.reply.repository.ReplyRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyCommandServiceImpl implements ReplyCommandService {
    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    @Override
    public ReplyResponseDTO.ReplyDTO createReply(Reply reply, Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new IllegalArgumentException("게시물이 존재하지 않습니다."));
        Reply replyEntity = replyRepository.save(Reply.builder().content(reply.getContent()).article(article).build());
        return ReplyResponseDTO.ReplyDTO.toDTO(replyEntity);
    }
}
