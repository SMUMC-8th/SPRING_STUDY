package com.example.umc8th.code.reply.service.command;

import com.example.umc8th.code.article.entity.Article;
import com.example.umc8th.code.article.repository.ArticleRepository;
import com.example.umc8th.code.reply.converter.ReplyConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.umc8th.code.reply.dto.ReplyRequestDTO;
import com.example.umc8th.code.reply.entity.Reply;
import com.example.umc8th.code.reply.repository.ReplyRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService {

    private final ReplyRepository ReplyRepository;
    private final ArticleRepository ArticleRepository;

    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto, Long articleId) {

        Article article = ArticleRepository.findById(articleId).orElse(null);
        //여기에 orElseThrow로 한번에 날리고 싶은데 없을 경우 error설정이 모르겠어요...
        return ReplyRepository.save(
                ReplyConverter.toEntity(dto, article));

    }
}



