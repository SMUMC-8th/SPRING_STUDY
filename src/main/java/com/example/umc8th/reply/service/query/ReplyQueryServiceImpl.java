package com.example.umc8th.reply.service.query;

import com.example.umc8th.reply.entity.Reply;
import com.example.umc8th.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyQueryServiceImpl implements ReplyQueryService {
    private final ReplyRepository replyRepository;

    @Override
    public Reply getReplyById(long replyId) {
        return replyRepository.findById(replyId).orElse(null);
    }

    @Override
    public List<Reply> getReplies() {
        return replyRepository.findAll();
    }
}
