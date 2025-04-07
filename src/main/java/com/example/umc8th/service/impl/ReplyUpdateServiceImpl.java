package com.example.umc8th.service.impl;

import com.example.umc8th.dto.ReplyRequestDTO;
import com.example.umc8th.entity.Reply;
import com.example.umc8th.exception.ReplyErrorCode;
import com.example.umc8th.exception.ReplyException;
import com.example.umc8th.repository.ReplyRepository;
import com.example.umc8th.service.ReplyUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyUpdateServiceImpl implements ReplyUpdateService {
    private final ReplyRepository replyRepository;

    @Override
    public Reply updateReply(Long id, ReplyRequestDTO.UpdateReplyDTO dto){
        Reply reply = replyRepository.findById(id).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND));
        reply.update(dto.getContent());
        return reply;
    }
}
