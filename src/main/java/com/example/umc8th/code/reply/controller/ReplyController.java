package com.example.umc8th.code.reply.controller;

import com.example.umc8th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.umc8th.code.reply.dto.ReplyRequestDTO;
import com.example.umc8th.code.reply.entity.Reply;
import com.example.umc8th.code.reply.dto.ReplyResponseDTO;

import com.example.umc8th.code.reply.service.command.ReplyCommandService;
import com.example.umc8th.code.reply.service.query.ReplyQueryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles/{articleId}/replies")
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    @Operation(summary = "댓글 생성")
    @PostMapping
    public CustomResponse<ReplyResponseDTO> createResponse(@PathVariable Long articleId,
                                                           @RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto, articleId);

        ReplyResponseDTO replyDTO = new ReplyResponseDTO(reply);
        return CustomResponse.onSuccess(replyDTO);
    }

    @Operation(summary = "댓글 수정")
    @PatchMapping("/{replyId}")
    public CustomResponse<Reply> updateReplyPatch(@PathVariable Long articleId, @RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply updatedReply = replyCommandService.saveAndUpdate(articleId, dto);
        return CustomResponse.onSuccess(updatedReply);
    }

    @Operation(summary = "댓글 수정")
    @PutMapping("/{replyId}")
    public CustomResponse<Reply> updateReplyPut(@PathVariable Long articleId, @RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply updatedReply = replyCommandService.saveAndUpdate(articleId, dto);
        return CustomResponse.onSuccess(updatedReply);
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{replyId}")
    public CustomResponse<String> deleteReply(@PathVariable Long articleId, @PathVariable Long replyId){
        replyCommandService.deleteReply(articleId, replyId);
        return CustomResponse.onSuccess("댓글 삭제 되었음");
    }


    @Operation(summary = "댓글 전체 조회", description = "특정 게시글의 모든 댓글을 조회합니다.")
    @GetMapping
    public CustomResponse<List<ReplyResponseDTO>> getAllReplies(@PathVariable Long articleId) {
        List<Reply> replies = replyQueryService.getRepliesByArticle(articleId);

//        List<ReplyResponseDTO> replyDTOs = new ArrayList<>();
//        for (Reply reply : replies) {
//            replyDTOs.add(new ReplyResponseDTO(reply));
//        }
        //아직 stream에 익숙치 않아서..
        List<ReplyResponseDTO> replyDTOs = replies.stream().map(ReplyResponseDTO::new).toList();
        return CustomResponse.onSuccess(replyDTOs);
    }

    @Operation(summary = "댓글 페이지네이션", description = "댓글 offset기반 페이지네이션 - 생성날짜순서로")
    @GetMapping("/offset")
    public CustomResponse<Page<ReplyResponseDTO>> getRepliesByArticleId(@PathVariable("articleId") Long articleId,
                                                             @RequestParam(defaultValue="0") int page,
                                                             @RequestParam(defaultValue = "10") int size){
        Page<Reply> replies = replyQueryService.findRepliesByArticleId(articleId, page, size);
        Page<ReplyResponseDTO> replyDTOs = replies.map(ReplyResponseDTO::new);
        return CustomResponse.onSuccess(replyDTOs);
    }
}
