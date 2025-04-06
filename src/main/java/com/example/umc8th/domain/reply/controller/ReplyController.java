package com.example.umc8th.domain.reply.controller;


import com.example.umc8th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.domain.reply.service.command.ReplyCommandService;
import com.example.umc8th.domain.reply.service.query.ReplyQueryService;
import com.example.umc8th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Reply API")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    // 생성
    @PostMapping("/article/{articleId}/replies")
    public CustomResponse<Reply> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto, Long articleId) {

        Reply reply = replyCommandService.createReply(dto, articleId);
        return CustomResponse.created(reply);
    }

    @GetMapping("/replies/{replyId}")
    public CustomResponse<Reply> getReply(@PathVariable Long replyId) {
        Reply reply = replyQueryService.getReply(replyId);
        return CustomResponse.ok(reply);
    }

    @GetMapping("/replies")
    public CustomResponse<List<Reply>> getAllReplies() {
        List<Reply> replies = replyQueryService.getAllReplies();
        return CustomResponse.ok(replies);
    }

//    @GetMapping("/article/{articleId}/replies")
//    public CustomResponse<List<Reply>> getAllReply(@PathVariable Long articleId) {
//
//    }
}
