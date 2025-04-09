package com.example.umc8th.domain.reply.controller;

import com.example.umc8th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc8th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.domain.reply.service.command.ReplyCommandService;
import com.example.umc8th.domain.reply.service.query.ReplyQueryService;
import com.example.umc8th.global.apiPayload.GlobalResponse;
import com.example.umc8th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "댓글 API")
public class ReplyController {
    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;


    @PostMapping("articles/{articleId}/replies")
    public GlobalResponse<ReplyResponseDTO.ReplyDTO> createReply(
            @RequestBody ReplyRequestDTO.CreateReplyDTO dto,
            @PathVariable Long articleId
    ) {
        ReplyResponseDTO.ReplyDTO reply = replyCommandService.createReply(dto, articleId);
        return GlobalResponse.created(reply);
    }

    @GetMapping("articles/{articleId}/replies")
    public GlobalResponse<ReplyResponseDTO.PageReplyDTO> getReplyList(
            @PathVariable Long articleId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        ReplyResponseDTO.PageReplyDTO replies = replyQueryService.getReplyList(articleId, page-1, size);
        return GlobalResponse.ok(replies);
    }

    @PutMapping("articles/{articleId}/replies/{replyId}")
    public GlobalResponse<ReplyResponseDTO.ReplyDTO> updateReply(
            @PathVariable Long articleId,
            @PathVariable Long replyId,
            @RequestBody ReplyRequestDTO.UpdateReplyDTO dto
    ) {
        ReplyResponseDTO.ReplyDTO reply = replyCommandService.updateReply(dto, articleId, replyId);
        return GlobalResponse.ok(reply);
    }

    @DeleteMapping("articles/{articleId}/replies/{replyId}")
    public GlobalResponse<ReplyResponseDTO.DeleteReplyDTO> deleteReply(
            @PathVariable Long articleId,
            @PathVariable Long replyId
    ) {
        ReplyResponseDTO.DeleteReplyDTO deleteReply = replyCommandService.deleteReply(articleId, replyId);
        return GlobalResponse.onSuccess(
                GeneralSuccessCode.NO_CONTENT_204.getCode(),
                GeneralSuccessCode.NO_CONTENT_204.getMessage(),
                deleteReply
        );
    }
}
