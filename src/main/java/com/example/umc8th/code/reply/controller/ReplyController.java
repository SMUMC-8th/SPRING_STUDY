package com.example.umc8th.code.reply.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.umc8th.code.reply.dto.ReplyRequestDTO;
import com.example.umc8th.code.reply.entity.Reply;
import com.example.umc8th.code.reply.dto.ReplyResponseDTO;
import com.example.umc8th.code.exception.CustomResponse;
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

    //// 댓글 생성
    @PostMapping
    public CustomResponse<ReplyResponseDTO> createResponse(@PathVariable Long articleId,@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto, articleId);

        ReplyResponseDTO replyDTO = new ReplyResponseDTO(reply);
        return CustomResponse.onSuccess(replyDTO);
    }

    //// 댓글 조회
    @GetMapping("/{replyId}")
    public CustomResponse<ReplyResponseDTO> getResponse(@PathVariable Long articleId, @PathVariable Long replyId) {
        Reply reply = replyQueryService.getReply(replyId);
        ReplyResponseDTO replyDTO = new ReplyResponseDTO(reply);
        return CustomResponse.onSuccess(replyDTO);
    }

    //// 댓글 전체 조회
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

}
