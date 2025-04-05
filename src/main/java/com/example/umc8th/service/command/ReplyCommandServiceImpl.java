package com.example.umc8th.service.command;

import com.example.umc8th.dto.ReplyRequestDTO;
import com.example.umc8th.entity.Reply;
import com.example.umc8th.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService {

    private final ReplyRepository ReplyRepository;

    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto) {
        return ReplyRepository.save(
                Reply.builder()
                        .content(dto.getContent())
                        .build()
        );
    }
}



