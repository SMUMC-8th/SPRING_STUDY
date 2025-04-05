package com.example.umc8th.domain.article.controller;

import com.example.umc8th.domain.article.dto.ArticleRequestDTO;
import com.example.umc8th.domain.article.dto.ArticleResponseDTO;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.exception.code.ArticleErrorCode;
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

    @PutMapping("/articles/{articleId}")
    public GlobalResponse<ArticleResponseDTO.ArticleDTO> updateArticle(
            @RequestBody ArticleRequestDTO.UpdateArticleDTO dto,
            @PathVariable Long articleId
    ) {
        if (dto.getContent().isEmpty() && dto.getTitle().isEmpty()) {   // 잘못된 요청이 들어온 경우 (둘 다 빈칸)
            throw new ArticleException(ArticleErrorCode.BAD_REQUEST_400);
        } else if (dto.getTitle().isEmpty()) {  // 내용만 수정하는 경우
            ArticleResponseDTO.ArticleDTO article = articleCommandService.updateArticleContent(dto, articleId);
            return GlobalResponse.ok(article);
        } else if (dto.getContent().isEmpty()) {    // 제목만 수정하는 경우
            ArticleResponseDTO.ArticleDTO article = articleCommandService.updateArticleTitle(dto, articleId);
            return GlobalResponse.ok(article);
        } else {    // 모든 요소를 수정하는 경우
            ArticleResponseDTO.ArticleDTO article = articleCommandService.updateArticleAll(dto, articleId);
            return GlobalResponse.ok(article);
        }
    }

    @DeleteMapping("articles/{articleId}")
    public GlobalResponse<ArticleResponseDTO.DeleteArticleDTO> deleteArticle(@PathVariable Long articleId) {
        ArticleResponseDTO.DeleteArticleDTO article = articleCommandService.deleteArticle(articleId);
        return GlobalResponse.ok(article);
    }
}
