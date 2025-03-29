package com.example.umc8th.article.controller;

import com.example.umc8th.article.dto.ArticleRequestDTO;
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
    public CustomResponse<Article> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        // service 에서 게시글 생성한 게시글 가져오기
        Article article = articleCommandService.createArticle(dto);

        return CustomResponse.onSuccess(article);
    }

    @GetMapping("/articles/{articleId}")
    // @PathVariable 을 이용하여 {}로 설정한 변수의 값을 가져온 이후 Long articleId에 담기. 참고로 GET method는 RequestBody 사용이 불가능합니다.
    public CustomResponse<Article> getArticle(@PathVariable("articleId") Long articleId) {
        Article article = articleQueryService.getArticle(articleId);

        return CustomResponse.onSuccess(article);
    }

    @GetMapping("/articles")
    public CustomResponse<List<Article>> getArticles() {
        List<Article> articles = articleQueryService.getArticles();

        return CustomResponse.onSuccess(articles);
    }
}

