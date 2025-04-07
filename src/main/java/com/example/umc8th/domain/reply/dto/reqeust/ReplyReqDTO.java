package com.example.umc8th.domain.reply.dto.reqeust;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

public class ReplyReqDTO {

//    @Getter
//    public static class CreateReplyDTO {
//
//        @NotBlank(message = "댓글 내용을 입력해주세요.")
//        private String content;
//    }

    @Builder
    public record CreateReplyDTO(
            String content
    ){}

    @Builder
    public record UpdateReplyDTO(
            String content
    ){}


}
