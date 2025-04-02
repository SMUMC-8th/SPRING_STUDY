package com.example.umc8th.domain.article.service.command;

import com.example.umc8th.domain.article.converter.ArticleConverter;
import com.example.umc8th.domain.article.dto.request.ArticleReqDTO;
import com.example.umc8th.domain.article.dto.response.ArticleResDTO;
import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.domain.article.exception.ArticleErrorCode;
import com.example.umc8th.domain.article.exception.ArticleException;
import com.example.umc8th.domain.article.repository.ArticleRepository;
import com.example.umc8th.domain.reply.entity.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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

    @Override
    public ArticleResDTO.UpdateArticleResDTO updateArticle(Long articleId, ArticleReqDTO.UpdateArticleReqDTO reqDTO) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        article.update(reqDTO.title(), reqDTO.content());
        return ArticleConverter.toUpdateArticleResDTO(article);
    }

    @Override
    public ArticleResDTO.ArticleLikeResDTO increaseLikeNum(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        article.incrementLikeNum();
        return ArticleConverter.toArticleLikeResDTO(article);
    }

    @Override
    public ArticleResDTO.DeleteArticleResDTO deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        // 해당 게시글에 연결된 모든 댓글을 소프트 딜리트
        if (article.getReplies() != null) {
            article.getReplies().forEach(Reply::softDelete);
        }

        article.softDelete();
        return ArticleConverter.toDeleteArticleResDTO(article);
    }
}
