package umc.week3.code.reply.dto;

import lombok.Getter;

public class ReplyRequestDTO {

    @Getter
    public static class CreateReplyDTO {
        private Long articleId;
        private String content;
    }
}
