package com.example.umc8th.domain.reply.controller;

import com.example.umc8th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc8th.domain.article.dto.response.ArticleResDTO;
import com.example.umc8th.domain.reply.dto.response.ReplyResDTO;
import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.domain.reply.dto.reqeust.ReplyReqDTO;
import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.domain.reply.service.command.ReplyCommandService;
import com.example.umc8th.domain.reply.service.query.ReplyQueryService;
import com.example.umc8th.global.apiPayload.success.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    @PostMapping("/articles/{articleId}/replies")
    public CustomResponse<ReplyResDTO.CreateReplyDTO> createReply(
            @PathVariable("articleId") Long articleId,
            @RequestBody ReplyReqDTO.CreateReplyDTO reqDTO) {

        ReplyResDTO.CreateReplyDTO resDTO = replyCommandService.createReply(reqDTO, articleId);

        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED, resDTO);
    }

    @GetMapping("/articles/{articleId}/replies/{replyId}")
    public CustomResponse<ReplyResDTO.PreviewReplyDTO> getReply(@PathVariable("replyId") Long replyId){
        ReplyResDTO.PreviewReplyDTO resDTO = replyQueryService.getReply(replyId);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK, resDTO);
    }

    @GetMapping("/articles/{articleId}/replies")
    public CustomResponse<ReplyResDTO.PreviewListReplyDTO> getReplies(@PathVariable("articleId") Long articleId){
        ReplyResDTO.PreviewListReplyDTO resDTO = replyQueryService.getRepliesByArticleId(articleId);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK, resDTO);
    }

    @PutMapping("/articles/{articleId}/replies/{replyId}")
    public CustomResponse<ReplyResDTO.UpdateReplyDTO> updateReply(
            @PathVariable("replyId") Long replyId,
            @RequestBody ReplyReqDTO.UpdateReplyDTO reqDTO) {
        ReplyResDTO.UpdateReplyDTO resDTO = replyCommandService.updateReply(reqDTO, replyId);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK, resDTO);
    }

    @DeleteMapping("/articles/{articleId}/replies/{replyId}")
    public CustomResponse<ReplyResDTO.DeleteReplyDTO> deleteReply(@PathVariable("replyId") Long replyId) {
        ReplyResDTO.DeleteReplyDTO resDTO = replyCommandService.deleteReply(replyId);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK, resDTO);
    }

}
