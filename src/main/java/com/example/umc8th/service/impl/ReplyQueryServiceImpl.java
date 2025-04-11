package com.example.umc8th.service.impl;

import com.example.umc8th.entity.Reply;
import com.example.umc8th.repository.ReplyRepository;
import com.example.umc8th.service.ReplyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {
    private final ReplyRepository replyRepository;

    public Reply getReply(Long id){
        return replyRepository.findById(id).get();
    }

    public List<Reply> getReplies(Long articleId){
        return replyRepository.findAll();
    }
}
