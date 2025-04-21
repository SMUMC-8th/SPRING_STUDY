package com.example.umc8th.service.query.impl;

import com.example.umc8th.entity.Article;
import com.example.umc8th.repository.ArticleRepository;
import com.example.umc8th.service.query.ArticleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {
    private final ArticleRepository articleRepository;

    // 페이지네이션 구현으로 삭제 예정
    @Override
    public List<Article> getArticles(){
        return articleRepository.findAll();
    }

    @Override
    public Article getArticle(Long id){
        //return articleRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return articleRepository.findById(id).get();
    }
    @Override
    public Page<Article> getArticlesByCursor(Integer cursor, Integer offset){
        Pageable pageable = PageRequest.of(cursor, offset);
        Page<Article> article = articleRepository.findAll(pageable);
        return article;
    }

}
