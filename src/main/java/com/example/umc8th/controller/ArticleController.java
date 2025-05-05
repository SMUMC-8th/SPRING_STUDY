package com.example.umc8th.controller;

import com.example.umc8th.converter.ReplyConverter;
import com.example.umc8th.dto.ArticleRequestDTO;
import com.example.umc8th.dto.ArticleResponseDTO;
import com.example.umc8th.dto.ReplyResponseDTO;
import com.example.umc8th.entity.Article;
import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.service.command.ArticleCommandService;
import com.example.umc8th.service.query.ArticleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
// 이 컨트롤러에만 cors 적용할 경우, 아래 주석 해제
//@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ArticleController {
    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping("/articles")
    public CustomResponse<ArticleResponseDTO.CreateArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto){
        Article article = articleCommandService.createArticle(dto);
        return CustomResponse.created(ArticleResponseDTO.CreateArticleResponseDTO.from(article));
    }

    @GetMapping("/articles/{articleId}")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> getArticle(@PathVariable("articleId") Long articleId){
        Article article = articleQueryService.getArticle(articleId);
        return CustomResponse.ok(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }

    @GetMapping("/articles")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewListDTO> getArticles(@RequestParam Integer cursor, @RequestParam Integer size) {
        Slice<Article> articles = articleQueryService.getArticlesByCursor(cursor, size);
        return CustomResponse.ok(ArticleResponseDTO.ArticlePreviewListDTO.from(articles));
    }

    @PutMapping("/articles/{articleId}")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> putArticles(@PathVariable("articleId") Long articleId, @RequestBody ArticleRequestDTO.UpdateArticleDTO dto){
        Article article = articleCommandService.updateArticle(articleId, dto);
        return CustomResponse.ok(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }

    @PatchMapping("/articles/{articleId}")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> patchArticles(@PathVariable("articleId") Long articleId, @RequestBody ArticleRequestDTO.UpdateArticleContentDTO dto){
        Article article = articleCommandService.updateArticleContent(articleId, dto);
        return CustomResponse.ok(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }


    @DeleteMapping("/articles/{articleId}")
    public CustomResponse<ArticleResponseDTO.ArticleDeleteDTO> deleteArticles(@PathVariable("articleId") Long articleId){
        Long deleted = articleCommandService.deleteArticle(articleId);
        return CustomResponse.ok(ArticleResponseDTO.ArticleDeleteDTO.from(deleted));
    }
}

