package com.example.umc8th.domain.reply.service.command;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleErrorCode;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import com.example.umc8th.domain.reply.converter.ReplyConverter;
import com.example.umc8th.domain.reply.dto.request.ReplyReqDTO;
import com.example.umc8th.domain.reply.dto.response.ReplyResDTO;
import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.domain.reply.repository.ReplyRepository;
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
    public ReplyResDTO.CreateReplyResDTO createReply(ReplyReqDTO.CreateReplyReqDTO reqDTO) {
        // 댓글 달 게시글 조회 -> 없으면 예외 처리
        Article article = articleRepository.findById(reqDTO.articleId())
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        // DTO를 Entity로 변환해서 reply 테이블에 저장
        Reply savedReply = replyRepository.save(ReplyConverter.toReply(reqDTO, article));

        // 저장 된 Entity를 reply로 변환 후 controller 단에 반환
        return ReplyConverter.toCreateReplyResponseDTO(savedReply);
    }
}
