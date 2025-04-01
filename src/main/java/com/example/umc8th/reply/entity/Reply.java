package com.example.umc8th.reply.entity;

import com.example.umc8th.article.entity.Article;
import com.example.umc8th.global.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reply")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;
}
