package com.example.umc8th.service.impl;

import com.example.umc8th.repository.ReplyRepository;
import com.example.umc8th.service.ReplyDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyDeleteServiceImpl implements ReplyDeleteService {
    private final ReplyRepository replyRepository;


    @Override
    public Long deleteReply(Long id){
        replyRepository.deleteById(id);
        return id;
    }
}
