package com.example.umc8th.article.controller;

import com.example.umc8th.article.dto.ArticleRequestDTO;
import com.example.umc8th.article.dto.ArticleResponseDTO;
import com.example.umc8th.article.entity.Article;
import com.example.umc8th.article.service.command.ArticleCommandService;
import com.example.umc8th.article.service.query.ArticleQueryService;
import com.example.umc8th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping("/articles")
    public CustomResponse<ArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        // service 에서 게시글 생성한 게시글 가져오기
        ArticleResponseDTO response = articleCommandService.createArticle(dto);

        return CustomResponse.onSuccess(response);
    }

    @GetMapping("/articles/{articleId}")
    // @PathVariable 을 이용하여 {}로 설정한 변수의 값을 가져온 이후 Long articleId에 담기. 참고로 GET method는 RequestBody 사용이 불가능합니다.
    public CustomResponse<ArticleResponseDTO> getArticle(@PathVariable("articleId") Long articleId) {
        Article article = articleQueryService.getArticle(articleId);

        return CustomResponse.onSuccess(ArticleResponseDTO.ArticleConverter(article));
    }

    @GetMapping("/articles")
    public CustomResponse<List<ArticleResponseDTO>> getArticles() {
        List<Article> articles = articleQueryService.getArticles();

        List<ArticleResponseDTO> response = articles.stream()
                .map(ArticleResponseDTO::ArticleConverter)
                .toList();

        return CustomResponse.onSuccess(response);
    }
}

