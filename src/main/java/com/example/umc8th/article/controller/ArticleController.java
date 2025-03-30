package com.example.umc8th.article.controller;

import com.example.umc8th.article.dto.ArticleRequestDTO;
import com.example.umc8th.article.entity.Article;
import com.example.umc8th.article.service.command.ArticleCommandService;
import com.example.umc8th.article.service.query.ArticleQueryService;
import com.example.umc8th.global.apiPayload.GlobalResponse;
import com.example.umc8th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping("/articles")
    public GlobalResponse<Article> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        Article article = articleCommandService.createArticle(dto);
        return GlobalResponse.onSuccess(GeneralSuccessCode.CREATED_201,article);
    }

    @GetMapping("/articles/{articleId}")
    public GlobalResponse<Article> getArticle(@PathVariable("articleId") Long articleId) {
        Article article = articleQueryService.getArticle(articleId);
        return GlobalResponse.ok(article);
    }

    @GetMapping("/articles")
    public GlobalResponse<List<Article>> getArticles() {
        List<Article> articles = articleQueryService.getArticles();
        return GlobalResponse.ok(articles);
    }
}
