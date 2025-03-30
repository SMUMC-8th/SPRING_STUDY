package com.example.umc8th.reply.service.query;

import com.example.umc8th.article.entity.Article;
import com.example.umc8th.article.repository.ArticleRepository;
import com.example.umc8th.reply.dto.ReplyResponseDTO;
import com.example.umc8th.reply.entity.Reply;
import com.example.umc8th.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyQueryServiceImpl implements ReplyQueryService {
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public List<ReplyResponseDTO.ReplyDTO> getReplyList(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new IllegalArgumentException("게시물이 존재하지 않습니다."));
        List<Reply> replies = replyRepository.findAllByArticle((article));
        List<ReplyResponseDTO.ReplyDTO> replyList = new ArrayList<>();
        for (Reply reply : replies) {
            replyList.add(ReplyResponseDTO.ReplyDTO.toDTO(reply));
        }
        return replyList;
    }
}
