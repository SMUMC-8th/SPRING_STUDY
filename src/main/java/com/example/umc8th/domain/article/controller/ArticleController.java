package com.example.umc8th.domain.article.controller;

import com.example.umc8th.domain.article.dto.ArticleRequestDTO;
import com.example.umc8th.domain.article.dto.ArticleResponseDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.service.command.ArticleCommandService;
import com.example.umc8th.domain.article.service.query.ArticleQueryService;
import com.example.umc8th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequiredArgsConstructor
@Tag(name = "게시글 API")
public class ArticleController {

    private final ArticleCommandService articleCommandService;
    private final ArticleQueryService articleQueryService;

    private ArticleController(ArticleCommandService articleCommandService, ArticleQueryService articleQueryService) {
        this.articleCommandService = articleCommandService;
        this.articleQueryService = articleQueryService;
    }

    @PostMapping("/articles")
    @Operation(summary = "게시글 생성 API", description = "게시글 생성하는 API")
    public CustomResponse<ArticleResponseDTO.CreateArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        Article article = articleCommandService.createArticle(dto);
        return CustomResponse.created(ArticleResponseDTO.CreateArticleResponseDTO.from(article));
    }

    @GetMapping("/articles/{articleId}")
    @Operation(summary = "게시글 조회 API", description = "게시글 하나 조회하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "COMMON200", description = "OK"),
            @ApiResponse(responseCode = "ARTICLE404", description = "게시글을 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = CustomResponse.class)))
    })
    @Parameter(name = "articleId", description = "찾을 게시글 ID")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> getArticle(@PathVariable("articleId") Long articleId) {
        Article article = articleQueryService.getArticle(articleId);
        return CustomResponse.ok(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }

    @GetMapping("/articles")
    @Operation(summary = "게시글 전체 조회 API", description = "게시글 전체 조회하는 API")
    @Parameters({
            @Parameter(name = "cursor", description = "커서 값, 처음이면 0"),
            @Parameter(name = "query", description = "쿼리 LIKE, ID")
    })
    public CustomResponse<ArticleResponseDTO.ArticlePreviewListDTO> getArticles(@RequestParam(value = "query", defaultValue = "LIKE")String query,
                                                                                @RequestParam("cursor") Long cursor,
                                                                                @RequestParam(value = "offset", defaultValue = "10") Integer offset) {
        Slice<Article> articles = articleQueryService.getArticles(query, cursor, offset);
        return CustomResponse.ok(ArticleResponseDTO.ArticlePreviewListDTO.from(articles));
    }

    @GetMapping("/articles/{articleId}/test")
    public CustomResponse<Article> getArticlesTest(@PathVariable Long articleId) {
        Article article = articleQueryService.getArticle(articleId);
        return CustomResponse.ok(article);
    }

    @PutMapping("/articles/{articleId}")
    @Operation(summary = "게시글 수정 API", description = "게시글 수정하는 API")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> updateArticle(@PathVariable("articleId") Long articleId,
                                                                              @RequestBody ArticleRequestDTO.UpdateArticleDTO dto) {
        Article article = articleCommandService.updateArticle(articleId, dto);
        return CustomResponse.ok(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }

    @PatchMapping("/articles/{articleId}")
    @Operation(summary = "좋아요 수 증가 API", description = "게시글 좋아요 수 증가 API")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> increaseLike(@PathVariable("articleId") Long articleId) {
        Article article = articleCommandService.increaseLike(articleId);
        return CustomResponse.ok(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }

    @DeleteMapping("/articles/{articleId}")
    @Operation(summary = "게시글 삭제 API", description = "게시글 삭제하는 API")
    public CustomResponse<Void> deleteArticle(@PathVariable("articleId") Long articleId) {
        articleCommandService.deleteArticle(articleId);
        return CustomResponse.ok(null);
    }
}
