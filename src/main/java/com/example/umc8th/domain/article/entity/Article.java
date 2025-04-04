package com.example.umc8th.domain.article.entity;

import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.global.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "like_num")
    private int likeNum;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Reply> replies;

    // Update method
    public void updateTitle(String title) {
        this.title = title;
    }
    public void updateContent(String content) {
        this.content = content;
    }
    public void updateLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }
    public void updateAll(String content, String title) {
        this.title = title;
        this.content = content;
    }
}
