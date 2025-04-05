package umc.week3.code.article.dto;

import lombok.Getter;
import umc.week3.code.article.entity.Article;

import java.time.LocalDateTime;

@Getter
public class ArticleResponseDTO {

    private final Long id;
    private final String title;
    private final String content;
    private final int likeNum;
    private final LocalDateTime createAt;

    public ArticleResponseDTO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.likeNum = article.getLikeNum();
        this.createAt = article.getCreateAt();
    }
}
