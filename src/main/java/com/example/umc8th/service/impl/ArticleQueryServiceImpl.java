package com.example.umc8th.service.impl;

import com.example.umc8th.entity.Article;
import com.example.umc8th.repository.ArticleRepository;
import com.example.umc8th.service.ArticleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {
    private final ArticleRepository articleRepository;

    @Override
    public List<Article> getArticles(){
        return articleRepository.findAll();
    }

    @Override
    public Article getArticle(Long id){
        //return articleRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return articleRepository.findById(id).get();
    }

}
