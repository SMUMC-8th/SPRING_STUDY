package com.example.umc8th.domain.article.dto;

import com.example.umc8th.domain.article.entity.Article;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class ArticleRequestDTO {

    @Getter
    public static class CreateArticleDTO {
        @Schema(description = "게시글 제목")
        private String title;
        @Schema(description = "게시글 내용")
        private String content;

        public Article toEntity() {
            return Article.builder()
                    .title(this.title)
                    .content(this.getContent())
                    .likeNum(0)
                    .build();
        }
    }

    @Getter
    public static class UpdateArticleDTO {
        private String title;
        private String content;

    }
}
