package com.example.umc8th.code.reply.service.query;

import com.example.umc8th.code.article.entity.Article;
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

    @Override
    public Reply getReply(Long id) {
        return replyRepository.findById(id)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));

    }

    @Override
    public List<Reply> getRepliesByArticle(Long articleId) {
        // 게시글 존재 확인
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isEmpty()) return List.of(); // 게시글 없으면 빈 리스트 반환

        return replyRepository.findAllByArticle(article.get());
    }
}
