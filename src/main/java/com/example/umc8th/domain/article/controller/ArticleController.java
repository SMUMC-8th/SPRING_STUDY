package com.example.umc8th.domain.article.controller;

import com.example.umc8th.domain.article.dto.ArticleRequestDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.service.command.ArticleCommandService;
import com.example.umc8th.domain.article.service.query.ArticleQueryService;
import com.example.umc8th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.umc8th.global.apiPayload.code.GeneralSuccessCode.OK;

// RestController 명시
@RestController
@Tag(name = "Article API")
// 생성자 의존성 주입을 위한 Annotation (private final로 정의된 필드에 의존성 주입)
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    // 생성이므로 POST method 사용
    @PostMapping("/articles")
    // 요청 시 데이터를 담을 DTO를 설정해주고 RequestBody라는 것을 명시
    public CustomResponse<Article> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        // service에서 게시글 생성한 게시글 가져오기
        Article article = articleCommandService.createArticle(dto);
        // CustomResponse에 article을 담아 성공했다고 응답하기
        return CustomResponse.created(article);
    }

    // 조회이므로 GET method 사용
    // 뒤에 ID 값을 놓도록 설정 ex) /article/1로 요청이 오면 1을 변수로 사용
    @GetMapping("/articles/{articleId}")
    // @PathVariable을 이용하여 {}로 설정한 변수의 값을 가져온 이후 Long articleId에 담기. 참고로 GET method는 RequestBody 사용이 불가능합니다.
    public CustomResponse<Article> getArticle(@PathVariable("articleId") Long articleId) {
        Article article = articleQueryService.getArticle(articleId);
        return CustomResponse.ok(article);
    }

    @GetMapping("/articles")
    public CustomResponse<List<Article>> getArticles() {
        List<Article> articles = articleQueryService.getArticles();
        return CustomResponse.ok(articles);
    }
}