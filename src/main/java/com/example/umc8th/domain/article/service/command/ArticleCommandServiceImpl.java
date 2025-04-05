package com.example.umc8th.domain.article.service.command;

import com.example.umc8th.domain.article.converter.ArticleConverter;
import com.example.umc8th.domain.article.dto.ArticleRequestDTO;
import com.example.umc8th.domain.article.dto.ArticleResponseDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.exception.code.ArticleErrorCode;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService {
    private final ArticleRepository articleRepository;

    @Override
    public ArticleResponseDTO.ArticleDTO createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        Article article = ArticleConverter.toArticle(dto);
        return ArticleConverter.toArticleDTO(articleRepository.save(article));
    }

    @Override
    public ArticleResponseDTO.ArticleDTO updateArticle(ArticleRequestDTO.UpdateArticleDTO dto, Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND_404));
        if (dto.getContent().isEmpty() && dto.getTitle().isEmpty()) {   // 잘못된 요청이 들어온 경우 (둘 다 빈칸)
            throw new ArticleException(ArticleErrorCode.BAD_REQUEST_400);
        } else if (dto.getTitle().isEmpty()) {  // 내용만 수정하는 경우
            article.updateContent(dto.getContent());
            return ArticleConverter.toArticleDTO(article);
        } else if (dto.getContent().isEmpty()) {    // 제목만 수정하는 경우
            article.updateTitle(dto.getTitle());
            return ArticleConverter.toArticleDTO(article);
        } else {    // 모든 요소를 수정하는 경우
            article.updateAll(dto.getContent(), dto.getTitle());
            return ArticleConverter.toArticleDTO(article);
        }
    }

    @Override
    public ArticleResponseDTO.DeleteArticleDTO deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND_404));
        articleRepository.delete(article);
        return ArticleConverter.toDeleteArticleDTO(articleId);
    }


}
