package com.example.umc8th.domain.reply.dto.reqeust;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class ReplyReqDTO {

    @Getter
    public static class CreateReplyDTO {

        @NotBlank(message = "댓글 내용을 입력해주세요.")
        private String content;

        // PathVariable 로 articleId 받음
        // @NotNull(message = "게시글 ID는 필수입니다.")
        // private Long articleId;
    }
}
