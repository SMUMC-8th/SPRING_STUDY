package umc.week3.code.reply.service.command;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.week3.code.reply.dto.ReplyRequestDTO;
import umc.week3.code.reply.entity.Reply;
import umc.week3.code.reply.repository.ReplyRepository;

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



