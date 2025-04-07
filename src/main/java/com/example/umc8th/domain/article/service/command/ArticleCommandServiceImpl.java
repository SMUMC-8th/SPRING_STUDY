package com.example.umc8th.domain.article.service.command;

import com.example.umc8th.domain.article.converter.ArticleConverter;
import com.example.umc8th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc8th.domain.article.dto.response.ArticleResDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Service 로 사용하겠다고 명시 (빈 주입)
@Service
// Transactional 을 사용하겠다고 명시. 모든 메소드가 하나의 Transaction 단위로 동작, 단일 메소드에도 선언 가능
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService{

    private final ArticleRepository articleRepository;

    @Override
    public ArticleResDTO.CreateArticleDTO createArticle(ArticleReqDTO.CreateArticleDTO reqDTO) {
        Article article = ArticleConverter.from(reqDTO);

        article = articleRepository.save(article);

        return ArticleConverter.toCreateArticleDTO(article);
    }
}