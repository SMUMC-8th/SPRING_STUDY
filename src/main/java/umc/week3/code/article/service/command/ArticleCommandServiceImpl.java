package umc.week3.code.article.service.command;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.week3.code.article.converter.ArticleConverter;
import umc.week3.code.article.dto.ArticleRequestDTO;
import umc.week3.code.article.repository.ArticleRepository;
import umc.week3.code.article.entity.Article;

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
