package com.example.umc8th.domain.reply.controller;

import com.example.umc8th.domain.reply.dto.request.ReplyReqDTO;
import com.example.umc8th.domain.reply.dto.response.ReplyResDTO;
import com.example.umc8th.domain.reply.service.command.ReplyCommandService;
import com.example.umc8th.domain.reply.service.query.ReplyQueryService;
import com.example.umc8th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/replies")
@Tag(name = "댓글 API", description = "댓글 관련 API입니다.")
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    @PostMapping("")
    @Operation(summary = "댓글 생성", description = "request로 넘긴 articleId로 조회한 게시글에 댓글을 생성합니다.")
    public CustomResponse<ReplyResDTO.CreateReplyResDTO> createReply(@RequestBody ReplyReqDTO.CreateReplyReqDTO requestDto) {
        ReplyResDTO.CreateReplyResDTO resDTO = replyCommandService.createReply(requestDto);
        return CustomResponse.onSuccess(HttpStatus.CREATED, resDTO);
    }

    @GetMapping("/article/{articleId}")
    @Operation(summary = "댓글 조회", description = "PathVariable로 받은 articleId로 조회한 게시글의 댓글을 전체 조회합니다.")
    public CustomResponse<ReplyResDTO.ReplyPreviewListDTO> getRepliesByArticleId(
            @PathVariable Long articleId) {
        ReplyResDTO.ReplyPreviewListDTO replies = replyQueryService.getRepliesByArticle(articleId);
        return CustomResponse.onSuccess(replies);
    }

    @GetMapping("/article/{articleId}/page")
    public CustomResponse<ReplyResDTO.ReplyPreviewListDTO> getRepliesByArticlePaginated(
            @PathVariable Long articleId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        ReplyResDTO.ReplyPreviewListDTO replies = replyQueryService.getRepliesByArticlePaginated(articleId, page, size);
        return CustomResponse.onSuccess(replies);
    }

    @PutMapping("/{replyId}")
    @Operation(summary = "댓글 수정", description = "특정 댓글(replyId)을 수정합니다.")
    public CustomResponse<ReplyResDTO.UpdateReplyResDTO> updateReply(@PathVariable Long replyId,
                                                                     @RequestBody ReplyReqDTO.UpdateReplyReqDTO reqDTO) {
        ReplyResDTO.UpdateReplyResDTO resDTO = replyCommandService.updateReply(replyId, reqDTO);
        return CustomResponse.onSuccess(resDTO);
    }

    @DeleteMapping("/{replyId}")
    @Operation(summary = "댓글 삭제", description = "특정 댓글(replyId)을 삭제합니다. (소프트 딜리트)")
    public CustomResponse<ReplyResDTO.DeleteReplyResDTO> deleteReply(@PathVariable Long replyId) {
        ReplyResDTO.DeleteReplyResDTO resDTO = replyCommandService.deleteReply(replyId);
        return CustomResponse.onSuccess(resDTO);
    }

}
