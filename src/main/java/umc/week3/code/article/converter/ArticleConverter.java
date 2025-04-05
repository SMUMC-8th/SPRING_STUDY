package umc.week3.code.article.converter;

import umc.week3.code.article.dto.ArticleRequestDTO;
import umc.week3.code.article.entity.Article;

public class ArticleConverter {
    public static Article toEntity(ArticleRequestDTO.CreateArticleDTO dto) {
        return Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }
}
