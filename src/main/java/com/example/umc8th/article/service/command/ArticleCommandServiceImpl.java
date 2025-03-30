package com.example.umc8th.article.service.command;

import com.example.umc8th.article.dto.ArticleRequestDTO;
import com.example.umc8th.article.entity.Article;
import com.example.umc8th.article.repository.ArticleRepository;
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
        return articleRepository.save(
                Article.builder()
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .build()
        );
    }
}
