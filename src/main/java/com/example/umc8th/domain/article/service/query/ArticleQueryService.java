package com.example.umc8th.domain.article.service.query;

import com.example.umc8th.domain.article.dto.ArticleResponseDTO;
import com.example.umc8th.domain.article.entity.Article;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ArticleQueryService {
    Article getArticle(Long id);
    Slice<Article> getArticles(String query, Long cursor, Integer offset);
}
