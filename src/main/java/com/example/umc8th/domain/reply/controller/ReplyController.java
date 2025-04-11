package com.example.umc8th.domain.reply.controller;

import com.example.umc8th.domain.reply.converter.ReplyConverter;
import com.example.umc8th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc8th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.domain.reply.service.command.ReplyCommandService;
import com.example.umc8th.domain.reply.service.query.ReplyQueryService;
import com.example.umc8th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
@Tag(name = "댓글 API")
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    @PostMapping
    @Operation(summary = "댓글 생성 API", description = "댓글 생성하는 API")
    public CustomResponse<ReplyResponseDTO.CreateReplyResponseDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        return CustomResponse.created(ReplyConverter.toCreateReplyResponseDTO(reply));
    }

    @GetMapping("/articles/{articleId}")
    @Operation(summary = "댓글 전체 조회 API", description = "댓글 전체 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewListDTO> getReplies(@PathVariable Long articleId) {
        List<Reply> replies = replyQueryService.getReplies(articleId);
        return CustomResponse.ok(ReplyConverter.toReplyPreviewListDTO(replies));
    }

    @GetMapping("/{replyId}")
    @Operation(summary = "댓글 조회 API", description = "댓글 하나 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> getReply(@PathVariable("replyId") Long replyId) {
        Reply reply = replyQueryService.getReply(replyId);
        return CustomResponse.ok(ReplyConverter.toReplyPreviewDTO(reply));
    }

    @PutMapping("/{replyId}")
    @Operation(summary = "댓글 수정 API")
    public CustomResponse<ReplyResponseDTO.ReplyUpdateResponseDTO> updateReply(
            @PathVariable("replyId") Long replyId,
            @RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply updatedReply = replyCommandService.updateReply(replyId, dto);
        return CustomResponse.ok(ReplyResponseDTO.ReplyUpdateResponseDTO.from(updatedReply));
    }

    @DeleteMapping("/{replyId}")
    @Operation(summary = "댓글 삭제 API")
    public CustomResponse<ReplyResponseDTO.ReplyDeleteResponseDTO> deleteReply(@PathVariable("replyId") Long replyId) {
        Reply deletedReply = replyCommandService.deleteReply(replyId);
        return CustomResponse.ok(ReplyResponseDTO.ReplyDeleteResponseDTO.from(deletedReply));
    }


}
