package com.example.umc8th.service.impl;

import com.example.umc8th.dto.ArticleRequestDTO;
import com.example.umc8th.entity.Article;
import com.example.umc8th.repository.ArticleRepository;
import com.example.umc8th.service.ArticleCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService {
    private final ArticleRepository articleRepository;

    @Override
    public Article createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        return articleRepository.save(dto.toEntity());
    }
}
