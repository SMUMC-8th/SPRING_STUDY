package com.example.umc8th.controller;

import com.example.umc8th.dto.ArticleRequestDTO;
import com.example.umc8th.dto.ArticleResponseDTO;
import com.example.umc8th.entity.Article;
import com.example.umc8th.global.apiPayload.CustomResponse;
import com.example.umc8th.service.ArticleCommandService;
import com.example.umc8th.service.ArticleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping("/articles")
    public CustomResponse<ArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto){
        Article article = articleCommandService.createArticle(dto);
        ArticleResponseDTO articleResponseDTO = new ArticleResponseDTO(
                article.getTitle(), article.getContent(), article.getLikeNum(), article.getCreatedAt(), article.getCreatedAt()
        );
        return CustomResponse.ok(articleResponseDTO);
    }

    @GetMapping("/articles/{articleId}")
    public CustomResponse<ArticleResponseDTO> getArticle(@PathVariable("articleId") Long articleId){
        Article article = articleQueryService.getArticle(articleId);
        ArticleResponseDTO articleResponseDTO = new ArticleResponseDTO(
                article.getTitle(), article.getContent(), article.getLikeNum(), article.getCreatedAt(), article.getCreatedAt()
        );
        return CustomResponse.ok(articleResponseDTO);
    }

    @GetMapping("/articles")
    public CustomResponse<List<ArticleResponseDTO>> getArticles() {
        List<Article> articles= articleQueryService.getArticles();
        List<ArticleResponseDTO> result = new ArrayList<>();
        for(Article article : articles){
            ArticleResponseDTO articleResponseDTO = new ArticleResponseDTO(
                    article.getTitle(), article.getContent(), article.getLikeNum(), article.getCreatedAt(), article.getCreatedAt()
            );
            result.add(articleResponseDTO);
        }
        return CustomResponse.ok(result);
    }
}

