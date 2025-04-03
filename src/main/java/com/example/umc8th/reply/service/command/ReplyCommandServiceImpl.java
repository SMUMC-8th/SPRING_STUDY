package com.example.umc8th.reply.service.command;

import com.example.umc8th.article.entity.Article;
import com.example.umc8th.article.exception.ArticleException;
import com.example.umc8th.article.exception.code.ArticleErrorCode;
import com.example.umc8th.article.repository.ArticleRepository;
import com.example.umc8th.reply.converter.ReplyConverter;
import com.example.umc8th.reply.dto.ReplyRequestDTO;
import com.example.umc8th.reply.dto.ReplyResponseDTO;
import com.example.umc8th.reply.entity.Reply;
import com.example.umc8th.reply.repository.ReplyRepository;
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
    public ReplyResponseDTO.ReplyDTO createReply(ReplyRequestDTO.CreateReplyDTO dto, Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.FORBIDDEN_403));
        Reply reply = ReplyConverter.toReply(dto,article);
        return ReplyConverter.toReplyDTO(replyRepository.save(reply));
    }
}
