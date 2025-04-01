package umc.week3.code.service.query;

import umc.week3.code.entity.Article;

import java.util.List;

public interface ArticleQueryService {
    List<Article> getArticles();
    Article getArticle(Long id);
}
