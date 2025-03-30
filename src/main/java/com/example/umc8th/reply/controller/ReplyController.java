package com.example.umc8th.reply.controller;

import com.example.umc8th.global.apiPayload.GlobalResponse;
import com.example.umc8th.reply.dto.ReplyRequestDTO;
import com.example.umc8th.reply.entity.Reply;
import com.example.umc8th.reply.service.command.ReplyCommandService;
import com.example.umc8th.reply.service.query.ReplyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReplyController {
    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;


    @PostMapping("/reply")
    public GlobalResponse<Reply> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        return GlobalResponse.ok(reply);
    }

    @GetMapping("/reply/{replyId}")
    public GlobalResponse<Reply> getReply(@PathVariable Long replyId) {
        Reply reply = replyQueryService.getReplyById(replyId);
        return GlobalResponse.ok(reply);
    }

    @GetMapping("/replies")
    public GlobalResponse<List<Reply>> getReplies() {
        List<Reply> replies = replyQueryService.getReplies();
        return GlobalResponse.ok(replies);
    }
}
