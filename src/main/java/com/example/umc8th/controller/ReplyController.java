package com.example.umc8th.controller;

import com.example.umc8th.dto.ReplyRequestDTO;
import com.example.umc8th.entity.Reply;
import com.example.umc8th.exception.CustomResponse;
import com.example.umc8th.service.command.ReplyCommandService;
import com.example.umc8th.service.query.ReplyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService ReplyQueryService;

    //// 댓글 생성
    @PostMapping("/reviews")
    public CustomResponse<Reply> createResponse(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        //지금 엔티티 반환인데 나중에 수정하자..
        return CustomResponse.onSuccess(reply);
    }

    //// 댓글 조회
    //이건 나중에 대댓글 다는 용으로 써야하나?

    //// 댓글 전체 조회
    @GetMapping("/reviews")
    public CustomResponse<List<Reply>> getAllResponse() {
        List<Reply> replies = ReplyQueryService.getReplies();
        return CustomResponse.onSuccess(replies);
    }
}
