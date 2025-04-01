package umc.week3.code.service.command;

import umc.week3.code.dto.ArticleRequestDTO;
import umc.week3.code.entity.Article;

public interface ArticleCommandService {
    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);
}
