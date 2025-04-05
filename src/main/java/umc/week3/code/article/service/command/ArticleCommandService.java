package umc.week3.code.article.service.command;

import umc.week3.code.article.dto.ArticleRequestDTO;
import umc.week3.code.article.entity.Article;

public interface ArticleCommandService {
    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);
}
