package com.example.umc8th.domain.article.controller;

import com.example.umc8th.domain.article.dto.ArticleRequestDTO;
import com.example.umc8th.domain.article.dto.ArticleResponseDTO;
import com.example.umc8th.domain.article.service.command.ArticleCommandService;
import com.example.umc8th.domain.article.service.query.ArticleQueryService;
import com.example.umc8th.global.apiPayload.GlobalResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "게시물 API")
public class ArticleController {
    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping("/articles")
    public GlobalResponse<ArticleResponseDTO.ArticleDTO> createArticle(
            @RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        ArticleResponseDTO.ArticleDTO article = articleCommandService.createArticle(dto);
        return GlobalResponse.created(article);
    }

    @GetMapping("/articles/{articleId}")
    public GlobalResponse<ArticleResponseDTO.ArticleDTO> getArticle(@PathVariable("articleId") Long articleId) {
        ArticleResponseDTO.ArticleDTO article = articleQueryService.getArticle(articleId);
        return GlobalResponse.ok(article);
    }

    @GetMapping("/articles")
    public GlobalResponse<ArticleResponseDTO.ArticleListDTO> getArticles() {
        ArticleResponseDTO.ArticleListDTO articles = articleQueryService.getArticles();
        return GlobalResponse.ok(articles);
    }
}
