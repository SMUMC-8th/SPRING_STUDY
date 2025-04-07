package com.example.umc8th.controller;

import com.example.umc8th.dto.ReplyRequestDTO;
import com.example.umc8th.entity.Reply;
import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.service.ReplyCommandService;
import com.example.umc8th.service.ReplyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    @PostMapping("/replies")
    public CustomResponse<Reply> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto){
        Reply reply = replyCommandService.createArticle(dto);
        return CustomResponse.ok(reply);
    }

    @GetMapping("/replies/{replyId}")
    public CustomResponse<Reply> getReply(@PathVariable("replyId") Long replyId){
        Reply reply = replyQueryService.getReply(replyId);
        return CustomResponse.ok(reply);
    }

    @GetMapping("replies")
    public CustomResponse<List<Reply>> getReplies(){
        List<Reply> replies = replyQueryService.getReplies();
        return CustomResponse.ok(replies);
    }
}
