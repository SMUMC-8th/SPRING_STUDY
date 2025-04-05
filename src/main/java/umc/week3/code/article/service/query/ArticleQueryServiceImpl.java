package umc.week3.code.article.service.query;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.week3.code.article.repository.ArticleRepository;
import umc.week3.code.exception.GeneralErrorCode;
import umc.week3.code.exception.GeneralException;
import umc.week3.code.article.entity.Article;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticle(Long id) {
        Optional<Article> findArticle = articleRepository.findById(id);
        return articleRepository.findById(id)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND_404));
    }
}
