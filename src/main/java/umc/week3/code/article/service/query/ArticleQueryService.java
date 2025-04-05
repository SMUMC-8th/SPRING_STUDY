package umc.week3.code.article.service.query;

import umc.week3.code.article.entity.Article;

import java.util.List;

public interface ArticleQueryService {
    List<Article> getArticles();
    Article getArticle(Long id);
}
