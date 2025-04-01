package com.example.umc8th.article.dto;

import com.example.umc8th.article.entity.Article;
import lombok.Getter;

public class ArticleRequestDTO {

    @Getter
    public static class CreateArticleDTO {
        private String title;
        private String content;

        public Article toEntity(){
            return Article.builder()
                    .title(title)
                    .content(content)
                    .build();
        }
    }
}
