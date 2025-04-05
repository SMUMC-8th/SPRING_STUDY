package umc.week3.code.article.dto;

import lombok.Getter;

public class ArticleRequestDTO {

    @Getter
    public static class CreateArticleDTO {
        private String title;
        private String content;
    }
}
