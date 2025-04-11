package com.example.umc8th.code.reply.service.query;

import com.example.umc8th.code.article.entity.Article;
import com.example.umc8th.code.article.enums.Active;
import com.example.umc8th.code.article.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.umc8th.code.reply.entity.Reply;
import com.example.umc8th.code.exception.GeneralErrorCode;
import com.example.umc8th.code.exception.GeneralException;
import com.example.umc8th.code.reply.repository.ReplyRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;



    @Override
    public List<Reply> getReplies() {
        return replyRepository.findAll();
    }

//    @Override
//    public Reply getReply(Long id) {
//        return replyRepository.findById(id)
//                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
//    }

    @Override
    public List<Reply> getRepliesByArticle(Long articleId) {
        // 게시글 ACTIVE한지
        Article article = articleRepository.findByIdAndActive(articleId, Active.ACTIVE)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));

        return replyRepository.findAllByArticle(article);
    }

    // 기존 댓글을 찾기
    @Override
    public Reply findActiveReply(Long articleId, Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));

        // 기존 댓글이 속한 게시글이 요청한 articleId와 일치하고, ACTIVE 상태인지 확인
        Article article = reply.getArticle();
        if (!article.getId().equals(articleId) || article.getActive() != Active.ACTIVE) {
            throw new GeneralException(GeneralErrorCode.NOT_FOUND_404);
        }

        return reply;
    }
}
