package com.example.umc8th.reply.controller;

import com.example.umc8th.article.dto.ArticleRequestDTO;
import com.example.umc8th.article.entity.Article;
import com.example.umc8th.article.repository.ArticleRepository;
import com.example.umc8th.article.service.command.ArticleCommandService;
import com.example.umc8th.article.service.query.ArticleQueryService;
import com.example.umc8th.global.apiPayload.GlobalResponse;
import com.example.umc8th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc8th.reply.dto.ReplyRequestDTO;
import com.example.umc8th.reply.dto.ReplyResponseDTO;
import com.example.umc8th.reply.entity.Reply;
import com.example.umc8th.reply.service.command.ReplyCommandService;
import com.example.umc8th.reply.service.query.ReplyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReplyController {
    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;


    @PostMapping("article/{articleId}/reply")
    public GlobalResponse<?> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto, @PathVariable Long articleId) {
        ReplyResponseDTO.ReplyDTO reply = replyCommandService.createReply(dto.toEntity(), articleId);
        return GlobalResponse.onSuccess(
                GeneralSuccessCode.CREATED_201.getCode(),
                GeneralSuccessCode.CREATED_201.getMessage(),
                reply);
    }

    @GetMapping("article/{articleId}/replies")
    public GlobalResponse<?> getReplyList(@PathVariable Long articleId) {
        List<ReplyResponseDTO.ReplyDTO> replies = replyQueryService.getReplyList(articleId);
        return GlobalResponse.ok(replies);
    }
}
