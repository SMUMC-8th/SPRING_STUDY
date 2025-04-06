package com.example.umc8th.domain.article.controller;

import com.example.umc8th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc8th.domain.article.dto.response.ArticleResDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.service.command.ArticleCommandService;
import com.example.umc8th.domain.article.service.query.ArticleQueryService;
import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.global.apiPayload.success.BaseSuccessCode;
import com.example.umc8th.global.apiPayload.success.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping("/articles")
    public CustomResponse<ArticleResDTO.CreateArticleDTO> createArticle(@RequestBody ArticleReqDTO.CreateArticleDTO dto) {
        // service 에서 게시글 생성한 게시글 가져오기
        ArticleResDTO.CreateArticleDTO resDTO = articleCommandService.createArticle(dto);

        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED, resDTO);
    }

    @GetMapping("/articles/{articleId}")
    // @PathVariable 을 이용하여 {}로 설정한 변수의 값을 가져온 이후 Long articleId에 담기. 참고로 GET method는 RequestBody 사용이 불가능합니다.
    public CustomResponse<ArticleResDTO.ArticlePreviewDTO> getArticle(@PathVariable("articleId") Long articleId) {
        ArticleResDTO.ArticlePreviewDTO resDTO = articleQueryService.getArticle(articleId);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK, resDTO);
    }

    @GetMapping("/articles")
    public CustomResponse<ArticleResDTO.ArticlePreviewListDTO> getArticles() {
        ArticleResDTO.ArticlePreviewListDTO resDTO = articleQueryService.getArticles();

        return CustomResponse.onSuccess(GeneralSuccessCode.OK, resDTO);
    }
}

