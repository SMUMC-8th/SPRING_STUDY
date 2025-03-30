package com.example.umc8th.reply.dto;

import com.example.umc8th.article.entity.Article;
import com.example.umc8th.reply.entity.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class ReplyRequestDTO {

    @Getter
    public static class CreateReplyDTO{
        private String content;

        public Reply toEntity() {
            return Reply.builder()
                    .content(content)
                    .build();
        }
    }
}
