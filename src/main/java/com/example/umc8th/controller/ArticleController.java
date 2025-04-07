package com.example.umc8th.controller;

import com.example.umc8th.converter.ReplyConverter;
import com.example.umc8th.dto.ArticleRequestDTO;
import com.example.umc8th.dto.ArticleResponseDTO;
import com.example.umc8th.dto.ReplyRequestDTO;
import com.example.umc8th.dto.ReplyResponseDTO;
import com.example.umc8th.entity.Article;
import com.example.umc8th.entity.Reply;
import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.service.ArticleCommandService;
import com.example.umc8th.service.ArticleDeleteService;
import com.example.umc8th.service.ArticleQueryService;
import com.example.umc8th.service.ArticleUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;
    private final ArticleUpdateService articleUpdateService;
    private final ArticleDeleteService articleDeleteService;

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
    public CustomResponse<ArticleResponseDTO.ArticlePreviewListDTO> getArticles() {
        List<Article> articles = articleQueryService.getArticles();
        return CustomResponse.ok(ArticleResponseDTO.ArticlePreviewListDTO.from(articles));
    }


    @PutMapping("/articles/{articleId}")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> putArticles(@PathVariable("articleId") Long articleId, @RequestBody ArticleRequestDTO.UpdateArticleDTO dto){
        Article article = articleUpdateService.updateArticle(articleId, dto);
        return CustomResponse.ok(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }

    @PatchMapping("/articles/{articleId}")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> patchArticles(@PathVariable("articleId") Long articleId, @RequestBody ArticleRequestDTO.UpdateArticleContentDTO dto){
        Article article = articleUpdateService.updateArticleContent(articleId, dto);
        return CustomResponse.ok(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }


    @DeleteMapping("/articles/{articleId}")
    public CustomResponse<ReplyResponseDTO.DeleteReplyDTO> deleteArticles(@PathVariable("articleId") Long articleId){
        Long deleted = articleDeleteService.deleteArticle(articleId);
        return CustomResponse.ok(ReplyConverter.toDeleteReplyDTO(deleted));
    }
}

