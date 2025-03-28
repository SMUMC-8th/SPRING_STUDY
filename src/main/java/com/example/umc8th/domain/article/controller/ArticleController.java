package com.example.umc8th.domain.article.controller;

import com.example.umc8th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc8th.domain.article.dto.response.ArticleResDTO;
import com.example.umc8th.domain.article.service.command.ArticleCommandService;
import com.example.umc8th.domain.article.service.query.ArticleQueryService;
import com.example.umc8th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
@Tag(name = "게시글 관련 API", description = "게시글 관련 API입니다.")
public class ArticleController {

    private final ArticleCommandService articleCommandService;
    private final ArticleQueryService articleQueryService;

    @PostMapping("")
    @Operation(summary = "게시글 작성", description = "request로 넘긴 title과 content로 게시글을 생성합니다.")
    public CustomResponse<ArticleResDTO.CreateArticleResDTO> createArticle(@RequestBody ArticleReqDTO.CreateArticleReqDTO reqDTO) {
        ArticleResDTO.CreateArticleResDTO resDTO = articleCommandService.createArticle(reqDTO);
        return CustomResponse.onSuccess(HttpStatus.CREATED, resDTO);
    }

    @GetMapping("/{articleId}")
    @Operation(summary = "게시글 단일 조회", description = "게시글의 ID로 게시글을 단일 조회합니다.")
    public CustomResponse<ArticleResDTO.ArticlePreviewDTO> getArticle(@PathVariable Long articleId) {
        ArticleResDTO.ArticlePreviewDTO resDTO = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(resDTO);
    }

    @GetMapping("")
    @Operation(summary = "전체 게시글 리스트 조회", description = "전체 게시글을 조회합니다.")
    public CustomResponse<ArticleResDTO.ArticlePreviewListDTO> getArticleList() {
        ArticleResDTO.ArticlePreviewListDTO articles = articleQueryService.getArticleList();
        return CustomResponse.onSuccess(articles);
    }
}
