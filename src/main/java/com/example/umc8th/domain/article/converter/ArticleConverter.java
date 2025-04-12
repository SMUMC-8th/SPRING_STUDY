package com.example.umc8th.domain.article.converter;

import com.example.umc8th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc8th.domain.article.dto.response.ArticleResDTO;
import com.example.umc8th.domain.article.entity.Article;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleConverter {

    // CreateArticleReqDTO -> Article Entity
    public static Article toArticle(ArticleReqDTO.CreateArticleReqDTO reqDTO) {
        return Article.builder()
                .title(reqDTO.title())
                .content(reqDTO.content())
                .likeNum(0)
                .deletedAt(null)
                .build();
    }

    // Article -> CreateArticleResDTO
    public static ArticleResDTO.CreateArticleResDTO toCreateArticleResDTO(Article article) {
        return ArticleResDTO.CreateArticleResDTO.builder()
                .id(article.getId())
                .createdAt(article.getCreatedAt())
                .build();
    }

    // Article -> UpdateArticleResDTO
    public static ArticleResDTO.UpdateArticleResDTO toUpdateArticleResDTO(Article article) {
        return ArticleResDTO.UpdateArticleResDTO.builder()
                .id(article.getId())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

    // Article -> ArticleLikeResDTO
    public static ArticleResDTO.ArticleLikeResDTO toArticleLikeResDTO(Article article) {
        return ArticleResDTO.ArticleLikeResDTO.builder()
                .id(article.getId())
                .likeNum(article.getLikeNum())
                .build();
    }

    // Article -> DeleteArticleResDTO
    public static ArticleResDTO.DeleteArticleResDTO toDeleteArticleResDTO(Article article) {
        return ArticleResDTO.DeleteArticleResDTO.builder()
                .id(article.getId())
                .deletedAt(article.getDeletedAt())
                .build();
    }

    // Article -> ArticlePreviewDTO
    public static ArticleResDTO.ArticlePreviewDTO toArticlePreviewDTO(Article article) {
        return ArticleResDTO.ArticlePreviewDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

    // List<Article> -> ArticlePreviewListDTO
    public static ArticleResDTO.ArticlePreviewListDTO toArticlePreviewListDTO(List<Article> articles) {
        return ArticleResDTO.ArticlePreviewListDTO.builder()
                .articlePreviewDtoList(articles.stream()
                        .map(ArticleConverter::toArticlePreviewDTO)
                        .toList())
                .build();
    }

    // List<Article> -> ArticlePreviewListDTO(cursor)
    public static ArticleResDTO.ArticlePreviewListDTO toArticlePreviewListDTOWithCursor(List<Article> articles,
                                                                                        boolean hasNext,
                                                                                        Long nextCursor) {
        return ArticleResDTO.ArticlePreviewListDTO.builder()
                .articlePreviewDtoList(articles.stream()
                        .map(ArticleConverter::toArticlePreviewDTO)
                        .toList())
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }
}
