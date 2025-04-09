package com.example.umc8th.domain.article.service.command;

import com.example.umc8th.domain.article.converter.ArticleConverter;
import com.example.umc8th.domain.article.dto.ArticleRequestDTO;
import com.example.umc8th.domain.article.dto.ArticleResponseDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.exception.code.ArticleErrorCode;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import com.example.umc8th.domain.article.service.query.ArticleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService {
    private final ArticleRepository articleRepository;
    private final ArticleQueryService articleQueryService;

    @Override
    public ArticleResponseDTO.ArticleDTO createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        Article article = ArticleConverter.toArticle(dto);
        return ArticleConverter.toArticleDTO(articleRepository.save(article));
    }

    @Override
    public ArticleResponseDTO.ArticleDTO updateArticle(ArticleRequestDTO.UpdateArticleDTO dto, Long articleId) {
        Article article = articleQueryService.isArticleExist(articleId);
        if (!dto.title().isEmpty()) {  // 제목 수정
            article.updateTitle(dto.title());
        }
        if (!dto.content().isEmpty()) {    // 내용 수정
            article.updateContent(dto.content());
        }
        return ArticleConverter.toArticleDTO(article);
    }

    @Override
    public ArticleResponseDTO.DeleteArticleDTO deleteArticle(Long articleId) {
        Article article = articleQueryService.isArticleExist(articleId);
        articleRepository.delete(article);
        return ArticleConverter.toDeleteArticleDTO(articleId);
    }
}
