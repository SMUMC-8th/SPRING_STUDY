package com.example.umc8th.code.article.controller;

import com.example.umc8th.code.article.dto.ArticleResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.umc8th.code.article.dto.ArticleRequestDTO;
import com.example.umc8th.code.exception.CustomResponse;
import com.example.umc8th.code.article.service.command.ArticleCommandService;
import com.example.umc8th.code.article.service.query.ArticleQueryService;
import com.example.umc8th.code.article.entity.Article;

import java.util.List;

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

    @Operation(summary = "게시물 수정", description = "전체 업데이트")
    @PutMapping("/articles/{articleId}")
    public CustomResponse<ArticleResponseDTO> updateArticlePut(@PathVariable("articleId") Long articleId, @RequestBody ArticleRequestDTO.CreateArticleDTO dto){
        Article updatedArticle = articleCommandService.saveAndUpdate(articleId, dto);
        ArticleResponseDTO responseDTO = new ArticleResponseDTO(updatedArticle);
        return CustomResponse.onSuccess(responseDTO);
    }

    @Operation(summary = "게시물 수정", description = "부분 업데이트")
    @PatchMapping("/articles/{articleId}")
    public CustomResponse<ArticleResponseDTO> updateArticlePatch(@PathVariable("articleId") Long articleId, @RequestBody ArticleRequestDTO.CreateArticleDTO dto){
        Article updatedArticle = articleCommandService.saveAndUpdate(articleId, dto);
        ArticleResponseDTO responseDTO = new ArticleResponseDTO(updatedArticle);
        return CustomResponse.onSuccess(responseDTO);
    }

    @Operation(summary = "게시물 삭제")
    @DeleteMapping("/articles/{articleId}")
    public CustomResponse<String> deleteArticle(@PathVariable("articleId") Long articleId){
        articleCommandService.deleteArticle(articleId);
        return CustomResponse.onSuccess("게시글이 정상적으로 삭제되었습니다.");
    }


    @Operation(summary = "게시글 하나 조회")
    @GetMapping("/articles/{articleId}")
    // @PathVariable을 이용하여 {}로 설정한 변수의 값을 가져온 이후 Long articleId에 담기. 참고로 GET method는 RequestBody 사용이 불가능합니다.
    public CustomResponse<ArticleResponseDTO> getArticle(@PathVariable("articleId") Long articleId) {
        //컨트롤러 그대로, 서비스 로직에서 active만 조회하도록 수정했음
        Article article = articleQueryService.getArticle(articleId);
        ArticleResponseDTO responseDTO = new ArticleResponseDTO(article);
        return CustomResponse.onSuccess(responseDTO);
    }

//    @Operation(summary = "게시글 전체 조회")
//    @GetMapping("/articles")
//    public CustomResponse<List<Article>> getArticles() {
//        List<Article> articles = articleQueryService.getArticles();
//        return CustomResponse.onSuccess(articles);
//    } - 커서 사용

    @Operation(summary = "게시글 cursor 기반 페이지네이션")
    @GetMapping("/articles/cursor")
    public CustomResponse<List<ArticleResponseDTO>> getArticlesByCursor(@RequestParam(required = false) Long lastArticleId,
                                                             @RequestParam(defaultValue = "10") int size) {
        List<Article> articles = articleQueryService.getArticlesByCursor(lastArticleId, size);
        List<ArticleResponseDTO> dtos = articles.stream().map(ArticleResponseDTO::new).toList();
        return CustomResponse.onSuccess(dtos);
        //stream은 리스트를 순서대로 반복
        //map은 각 article 객체를 ArticleResponseDTO로 변환하고
        //.map(article -> new ArticleResponseDTO(article))
        //toList()로 스트림으로 처리한 결과를 리스트로 만듦
    }
}
