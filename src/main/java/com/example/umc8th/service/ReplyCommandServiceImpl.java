package com.example.umc8th.service;

import com.example.umc8th.dto.ReplyRequestDTO;
import com.example.umc8th.entity.Reply;
import com.example.umc8th.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService {
    private final ReplyRepository replyRepository;

    @Override
    public Reply createArticle(ReplyRequestDTO.CreateReplyDTO dto){
        return replyRepository.save(
                Reply.builder()
                        .content(dto.getContent())
                        .build()
        );
    }
}
