package com.example.umc8th.service.command;

import com.example.umc8th.dto.ArticleRequestDTO;
import com.example.umc8th.entity.Article;
import com.example.umc8th.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
