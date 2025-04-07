package com.example.umc8th.dto;

import com.example.umc8th.entity.Article;
import lombok.Getter;

public class ArticleRequestDTO {

    @Getter
    public static class CreateArticleDTO {
        private String title;
        private String content;
        public Article toEntity(){
            return Article.builder()
                    .title(this.getTitle())
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

    @Getter
    public static class UpdateArticleContentDTO {
        private String content;
    }

    @Getter
    public static class DeleteArticleDTO {
        private Long id;
    }
}