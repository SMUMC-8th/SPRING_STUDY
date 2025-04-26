package com.example.umc8th.domain.reply.entity;

import com.example.umc8th.domain.article.entity.Article;
import com.example.umc8th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// JPA가 해당 클래스가 Entity라는 것을 인식하도록 해주는 Annotation
@Entity
// 테이블 이름을 지정하기 위해 @Table 사용
@Table(name = "reply")
// 빌더 패턴 사용
@Builder
// 기본 생성자 추가
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 모든 인자를 가지는 생성자 추가 (private으로 선언하여 객체 생성을 Builder 패턴 제한)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
// Getter 생성
@Getter
@EntityListeners(AuditingEntityListener.class)
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
