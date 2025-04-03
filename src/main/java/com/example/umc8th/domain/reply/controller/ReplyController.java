package com.example.umc8th.domain.reply.controller;

import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.domain.reply.dto.reqeust.ReplyRequestDTO;
import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.domain.reply.service.command.ReplyCommandService;
import com.example.umc8th.domain.reply.service.query.ReplyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    @PostMapping("/articles/{articleId}/replies")
    public CustomResponse<Reply> createReply(
            @PathVariable("articleId") Long articleId,
            @RequestBody ReplyRequestDTO.CreateReplyDTO dto) {

        Reply reply = replyCommandService.createReply(dto, articleId);

        return CustomResponse.onSuccess(reply);
    }

    @GetMapping("/articles/{articleId}/replies/{replyId}")
    public CustomResponse<Reply> getReply(@PathVariable("replyId") Long replyId){
        Reply reply = replyQueryService.getReply(replyId);

        return CustomResponse.onSuccess(reply);
    }

    @GetMapping("/articles/{articleId}/replies")
    public CustomResponse<List<Reply>> getReplies(@PathVariable("articleId") Long articleId){
        List<Reply> replies = replyQueryService.getRepliesByArticleId(articleId);

        return CustomResponse.onSuccess(replies);
    }

}
