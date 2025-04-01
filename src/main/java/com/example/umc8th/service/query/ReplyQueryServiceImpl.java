package com.example.umc8th.service.query;

import com.example.umc8th.entity.Reply;
import com.example.umc8th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc8th.global.apiPayload.code.GeneralException;
import com.example.umc8th.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyRepository replyRepository;

    @Override
    public List<Reply> getReplies() {
        return replyRepository.findAll();
    }

    @Override
    public Reply getReply(Long id) {
        Optional<Reply> reply = replyRepository.findById(id);
//        Reply ressultReply = reply.isPresent() ? reply.get() : null;
        if(reply.isPresent()) {
            return reply.get();
        }
//        return new GeneralException(GeneralErrorCode.NOT_FOUND_404);
        return replyRepository.findById(id)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));

    }
}
