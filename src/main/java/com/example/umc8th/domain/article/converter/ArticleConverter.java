package com.example.umc8th.domain.article.converter;

import com.example.umc8th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc8th.domain.article.dto.response.ArticleResDTO;
import com.example.umc8th.domain.article.entity.Article;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleConverter {

    // CreateArticleDTO -> Article Entity
    public static Article from(ArticleReqDTO.CreateArticleDTO requestDTO) {
        return Article.builder()
                .title(requestDTO.title())
                .content(requestDTO.content())
                .likeNum(0)
                .build();
    }

    // Article Entity -> CreateArticleDTO
    public static ArticleResDTO.CreateArticleDTO toCreateArticleDTO(Article article) {
        return ArticleResDTO.CreateArticleDTO.builder()
                .id(article.getId())
                .createdAt(article.getCreatedAt())
                .build();
    }

    // Article Entity -> ArticlePreviewDTO
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

    // ArticlePreviewDTO -> ArticlePreviewListDTO
    public static ArticleResDTO.ArticlePreviewListDTO toArticlePreviewListCursorPaginationDTO(List<Article> articles, boolean hasNext, Long nextCursor) {
        List<ArticleResDTO.ArticlePreviewDTO> previewDTOList = articles.stream()
                .map(ArticleConverter::toArticlePreviewDTO).collect(Collectors.toList());

        return ArticleResDTO.ArticlePreviewListDTO.builder()
                .articlePreviewListDTO(previewDTOList)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }

    // ArticlePreviewDTO -> ArticlePreviewListDTO
    public static ArticleResDTO.ArticlePreviewListDTO toArticlePreviewListDTO(List<Article> articles) {
        List<ArticleResDTO.ArticlePreviewDTO> previewDTOList = articles.stream()
                .map(ArticleConverter::toArticlePreviewDTO).collect(Collectors.toList());

        return ArticleResDTO.ArticlePreviewListDTO.builder()
                .articlePreviewListDTO(previewDTOList)
                .build();
    }

    // Article Entity -> UpdateArticleDTO
    public static ArticleResDTO.UpdateArticleDTO toUpdateArticleDTO(Article article) {
        return ArticleResDTO.UpdateArticleDTO.builder()
                .id(article.getId())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

    // Article Entity(articleId) -> DeleteArticleDTO
    public static ArticleResDTO.DeleteArticleDTO toDeleteArticleDTO(Long articleId) {
        return ArticleResDTO.DeleteArticleDTO.builder()
                .id(articleId)
                .build();
    }
}
