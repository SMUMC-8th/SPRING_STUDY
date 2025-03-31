package com.example.umc8th.reply.dto;

import com.example.umc8th.article.entity.Article;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReplyRequestDTO {

    @Getter
    public static class CreateReplyDTO {

        @NotBlank(message = "댓글 내용을 입력해주세요.")
        private String content;

        // PathVariable 로 articleId 받음
        // @NotNull(message = "게시글 ID는 필수입니다.")
        // private Long articleId;
    }
}
