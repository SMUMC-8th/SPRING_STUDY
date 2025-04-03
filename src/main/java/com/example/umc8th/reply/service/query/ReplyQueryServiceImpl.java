package com.example.umc8th.reply.service.query;

import com.example.umc8th.article.entity.Article;
import com.example.umc8th.article.exception.ArticleException;
import com.example.umc8th.article.exception.code.ArticleErrorCode;
import com.example.umc8th.article.repository.ArticleRepository;
import com.example.umc8th.reply.converter.ReplyConverter;
import com.example.umc8th.reply.dto.ReplyResponseDTO;
import com.example.umc8th.reply.entity.Reply;
import com.example.umc8th.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyQueryServiceImpl implements ReplyQueryService {
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public ReplyResponseDTO.ReplyListDTO getReplyList(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND_404));
        List<Reply> replies = replyRepository.findAllByArticle(article);
        return ReplyConverter.toReplyListDTO(replies);
    }
}
