package com.example.umc8th.reply.service.command;

import com.example.umc8th.reply.dto.ReplyRequestDTO;
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

    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto) {
        return replyRepository.save(
                Reply.builder()
                        .content(dto.getContent())
                        .build()
        );
    }
}
