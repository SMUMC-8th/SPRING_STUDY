package com.example.umc8th.controller;

import com.example.umc8th.converter.ReplyConverter;
import com.example.umc8th.dto.ReplyRequestDTO;
import com.example.umc8th.dto.ReplyResponseDTO;
import com.example.umc8th.entity.Reply;
import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.service.ReplyCommandService;
import com.example.umc8th.service.ReplyQueryService;
import com.example.umc8th.service.impl.ReplyDeleteServiceImpl;
import com.example.umc8th.service.impl.ReplyUpdateServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;
    private final ReplyUpdateServiceImpl replyUpdateServiceImpl;
    private final ReplyDeleteServiceImpl replyDeleteServiceImpl;

    @PostMapping("/replies")
    public CustomResponse<ReplyResponseDTO.CreateReplyResponseDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto){
        Reply reply = replyCommandService.createArticle(dto);
        return CustomResponse.created(ReplyConverter.toCreateReplyResponseDTO(reply));
    }

    @GetMapping("/replies/{replyId}")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> getReply(@PathVariable("replyId") Long replyId){
        Reply reply = replyQueryService.getReply(replyId);
        return CustomResponse.ok(ReplyConverter.toReplyPreviewDTO(reply));
    }

    @GetMapping("/articles/{articleId}/replies")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewListDTO> getReplies(@PathVariable("articleId") Long articleId){
        List<Reply> replies = replyQueryService.getReplies(articleId);
        return CustomResponse.ok(ReplyConverter.toReplyPreviewListDTO(replies));
    }

    @PatchMapping("/replies/{replyId}")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> patchReplies(@PathVariable("replyId") Long replyId, @RequestBody ReplyRequestDTO.UpdateReplyDTO dto){
        Reply reply = replyUpdateServiceImpl.updateReply(replyId, dto);
        return CustomResponse.ok(ReplyConverter.toReplyPreviewDTO(reply));
    }

    @DeleteMapping("/replies/{replyId}")
    public CustomResponse<ReplyResponseDTO.DeleteReplyDTO> deleteReplies(@PathVariable("replyId") Long replyId){
        Long replied = replyDeleteServiceImpl.deleteReply(replyId);
        return CustomResponse.ok(ReplyConverter.toDeleteReplyDTO(replied));
    }

}
