package umc.week3.code.reply.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.week3.code.reply.dto.ReplyRequestDTO;
import umc.week3.code.reply.dto.ReplyResponseDTO;
import umc.week3.code.reply.entity.Reply;
import umc.week3.code.exception.CustomResponse;
import umc.week3.code.reply.service.command.ReplyCommandService;
import umc.week3.code.reply.service.query.ReplyQueryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService ReplyQueryService;

    //// 댓글 생성
    @PostMapping("/reviews")
    public CustomResponse<Reply> createResponse(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        //지금 엔티티 반환인데 나중에 수정하자..
        return CustomResponse.onSuccess(reply);
    }

    //// 댓글 조회
    @GetMapping("/reviews/{reviewsId}")
    public CustomResponse<Reply> getResponse(@PathVariable Long reviewsId) {
        Reply reply = ReplyQueryService.getReply(reviewsId);
        ReplyResponseDTO replyDTO = new ReplyResponseDTO(reply);
        return CustomResponse.onSuccess(reply);
    }

    //// 댓글 전체 조회
    @GetMapping("/reviews")
    public CustomResponse<List<Reply>> getAllResponse() {
        List<Reply> replies = ReplyQueryService.getReplies();
        return CustomResponse.onSuccess(replies);
    }
}
