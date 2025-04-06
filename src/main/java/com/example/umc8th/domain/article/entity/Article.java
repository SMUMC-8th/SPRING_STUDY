package com.example.umc8th.domain.article.entity;


import com.example.umc8th.domain.reply.entity.Reply;
import com.example.umc8th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

// JPA가 해당 클래스가 Entity라는 것을 인식하도록 해주는 Annotation
@Entity
// 테이블 이름을 지정하기 위해 @Table 사용
@Table(name = "article")
// 빌더 패턴 사용
@Builder
// 기본 생성자 추가
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 모든 인자를 가지는 생성자 추가 (private으로 선언하여 객체 생성을 Builder 패턴 제한)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
// Getter 생성
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Article extends BaseEntity {
    // 해당 필드(Long id)를 PK(Primary key)로 지정
    @Id
    // PK의 생성 전략 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Column의 이름을 지정하기 위해 사용
    @Column(name = "title")
    private String title;

    // Column의 이름을 지정하기 위해 사용
    @Column(name = "content")
    private String content;

    // Column의 이름을 지정하기 위해 사용
    @Column(name = "like_num")
    private int likeNum;


    // 1:N 매핑, fetchType을 LAZY로 변경 (default = EAGER)
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Reply> replies;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void increaseLike() {
        this.likeNum++;
    }
}
