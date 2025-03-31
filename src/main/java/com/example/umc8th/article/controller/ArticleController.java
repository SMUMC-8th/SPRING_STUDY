package com.example.umc8th.article.controller;

import com.example.umc8th.article.dto.ArticleRequestDTO;
import com.example.umc8th.article.dto.ArticleResponseDTO;
import com.example.umc8th.article.entity.Article;
import com.example.umc8th.article.service.command.ArticleCommandService;
import com.example.umc8th.article.service.query.ArticleQueryService;
import com.example.umc8th.global.apiPayload.GlobalResponse;
import com.example.umc8th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "게시물 API")
public class ArticleController {
    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping("/articles")
    public GlobalResponse<?> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        ArticleResponseDTO.ArticleDTO article = articleCommandService.createArticle(dto.toEntity());
        return GlobalResponse.onSuccess(
                GeneralSuccessCode.CREATED_201.getCode(),
                GeneralSuccessCode.CREATED_201.getMessage(),
                article);
    }

    @GetMapping("/articles/{articleId}")
    public GlobalResponse<?> getArticle(@PathVariable("articleId") Long articleId) {
        ArticleResponseDTO.ArticleDTO article = articleQueryService.getArticle(articleId);
        return GlobalResponse.ok(article);
    }

    @GetMapping("/articles")
    public GlobalResponse<?> getArticles() {
        List<ArticleResponseDTO.ArticleDTO> articles = articleQueryService.getArticles();
        return GlobalResponse.ok(articles);
    }
}
