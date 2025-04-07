package com.example.umc8th.domain.article.entity;

import com.example.umc8th.domain.article.dto.response.ArticleResDTO;
import com.example.umc8th.global.entity.BaseTimeEntity;
import com.example.umc8th.domain.reply.entity.Reply;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Article extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "like_num")
    private Integer likeNum;

    @OneToMany(mappedBy = "article")
    private List<Reply> replies = new ArrayList<Reply>();

    public void updateTitle(String title) {
        this.title = title;
    }
    public void updateContent(String content) {
        this.content = content;
    }
}
