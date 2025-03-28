package com.example.umc8th.domain.article.service.command;

import com.example.umc8th.domain.article.converter.ArticleConverter;
import com.example.umc8th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc8th.domain.article.dto.response.ArticleResDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService{

    private final ArticleRepository articleRepository;

    @Override
    public ArticleResDTO.CreateArticleResDTO createArticle(ArticleReqDTO.CreateArticleReqDTO reqDTO) {
        // 로직 생각 : reqDTO를 Article Entity로 변환 -> Entity를 save -> Entity를 resDTO로 변환 -> resDTO return
        Article article = ArticleConverter.toArticle(reqDTO);
        articleRepository.save(article);
        return ArticleConverter.toCreateArticleResDTO(article);
    }
}
