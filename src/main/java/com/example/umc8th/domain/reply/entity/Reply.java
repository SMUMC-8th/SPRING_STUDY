package com.example.umc8th.domain.reply.entity;

import com.example.umc8th.domain.article.entity.Article;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
public class Reply {

    // 해당 필드(Long id)를 PK(Primary key)로 지정
    @Id
    // PK의 생성 전략 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Column의 이름을 지정하기 위해 사용
    @Column(name = "content")
    private String content;

    // 해당 Column에 생성시간 자동 mapping
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 해당 Column에 수정시간 자동 mapping
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // N:1 매핑, fetchType을 LAZY로 변경 (default = EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    // 해당 article을 article_id라는 이름으로 Column 추가 (실제 객체가 아닌 Long id를 저장하기에 이름을 article_id로 지정)
    @JoinColumn(name = "article_id")
    private Article article;
}
