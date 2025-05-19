package com.example.umc8th.code.article.service.command;

import com.example.umc8th.code.article.converter.ArticleConverter;
import com.example.umc8th.code.article.enums.Active;

import com.example.umc8th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc8th.global.apiPayload.code.GeneralException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.umc8th.code.article.dto.ArticleRequestDTO;
import com.example.umc8th.code.article.repository.ArticleRepository;
import com.example.umc8th.code.article.entity.Article;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService {

    private final ArticleRepository articleRepository;

    @Override
    public Article createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        return articleRepository.save(ArticleConverter.toEntity(dto));
    }

    @Override
    public Article saveAndUpdate(Long id, ArticleRequestDTO.CreateArticleDTO dto) {

        Article article = articleRepository.findById(id).orElseThrow(()->new GeneralException(GeneralErrorCode.NOT_FOUND_404));
        article.update(dto); //영속 상태의 엔티티 제목과 내용 변경
        return article;
    }

    @Override
    public Article findActiveArticle(Long id) {
        return articleRepository.findByIdAndActive(id, Active.ACTIVE)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
    }

    @Override
    public void deleteArticle(Long id) {
        Article article = articleRepository.findByIdAndActive(id, Active.ACTIVE)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
        article.softDelete();
    }
}
