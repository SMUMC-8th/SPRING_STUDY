package com.example.umc8th.service;

import com.example.umc8th.entity.Reply;
import com.example.umc8th.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService{
    private final ReplyRepository replyRepository;

    public Reply getReply(Long id){
        return replyRepository.findById(id).get();
    }

    public List<Reply> getReplies(){
        return replyRepository.findAll();
    }
}
