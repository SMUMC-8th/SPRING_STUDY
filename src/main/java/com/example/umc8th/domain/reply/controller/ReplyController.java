package com.example.umc8th.domain.reply.controller;

import com.example.umc8th.global.apiPayload.GlobalResponse;
import com.example.umc8th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc8th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc8th.domain.reply.service.command.ReplyCommandService;
import com.example.umc8th.domain.reply.service.query.ReplyQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "댓글 API")
public class ReplyController {
    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;


    @PostMapping("article/{articleId}/reply")
    public GlobalResponse<ReplyResponseDTO.ReplyDTO> createReply(
            @RequestBody ReplyRequestDTO.CreateReplyDTO dto,
            @PathVariable Long articleId) {
        ReplyResponseDTO.ReplyDTO reply = replyCommandService.createReply(dto, articleId);
        return GlobalResponse.created(reply);
    }

    @GetMapping("article/{articleId}/replies")
    public GlobalResponse<ReplyResponseDTO.ReplyListDTO> getReplyList(@PathVariable Long articleId) {
        ReplyResponseDTO.ReplyListDTO replies = replyQueryService.getReplyList(articleId);
        return GlobalResponse.ok(replies);
    }
}
