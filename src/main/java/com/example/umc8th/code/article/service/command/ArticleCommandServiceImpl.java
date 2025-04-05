package com.example.umc8th.code.article.service.command;

import com.example.umc8th.code.article.converter.ArticleConverter;
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
}
