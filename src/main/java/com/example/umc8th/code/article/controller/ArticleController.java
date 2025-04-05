package com.example.umc8th.code.article.controller;

import com.example.umc8th.code.article.dto.ArticleResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.umc8th.code.article.dto.ArticleRequestDTO;
import com.example.umc8th.code.exception.CustomResponse;
import com.example.umc8th.code.article.service.command.ArticleCommandService;
import com.example.umc8th.code.article.service.query.ArticleQueryService;
import com.example.umc8th.code.article.entity.Article;

import java.util.List;

// RestController 명시
@RestController
@Tag(name = "Article API")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    //// 게시글 생성
    @PostMapping("/articles")
    // 요청 시 데이터를 담을 DTO를 설정해주고 RequestBody라는 것을 명시
    public CustomResponse<ArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        // service에서 게시글 생성한 게시글 가져오기
        Article article = articleCommandService.createArticle(dto);
        // dto로 변환해서 반환
        ArticleResponseDTO responseDTO = new ArticleResponseDTO(article);
        return CustomResponse.onSuccess(responseDTO);
    }

    //// 게시글 하나 조회
    @GetMapping("/articles/{articleId}")
    // @PathVariable을 이용하여 {}로 설정한 변수의 값을 가져온 이후 Long articleId에 담기. 참고로 GET method는 RequestBody 사용이 불가능합니다.
    public CustomResponse<ArticleResponseDTO> getArticle(@PathVariable("articleId") Long articleId) {
        // 읽기전용으로 했던 쿼리서비스에서 불러옴
        Article article = articleQueryService.getArticle(articleId);
        ArticleResponseDTO responseDTO = new ArticleResponseDTO(article);
        return CustomResponse.onSuccess(responseDTO);
    }

    //// 게시글 전체 조회
    @GetMapping("/articles")
    public CustomResponse<List<Article>> getArticles() {
        // 쿼리서비스 메소드 사용
        List<Article> articles = articleQueryService.getArticles();
        return CustomResponse.onSuccess(articles);
    }
}
