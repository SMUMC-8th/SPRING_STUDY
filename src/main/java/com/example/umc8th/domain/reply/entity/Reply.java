package com.example.umc8th.domain.reply.entity;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    public void update(String content) {
        this.content = content;
    }

    public void softDelete() {
        deletedAt = LocalDateTime.now();
    }

    public void cancelDelete() {
        deletedAt = null;
    }
}
