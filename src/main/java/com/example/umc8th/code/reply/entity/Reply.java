package com.example.umc8th.code.reply.entity;

import com.example.umc8th.code.article.dto.ArticleRequestDTO;
import com.example.umc8th.code.article.enums.Active;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.example.umc8th.code.article.entity.Article;

import java.time.LocalDateTime;

@Entity
@Table(name = "reply")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Column의 이름을 지정하기 위해 사용
    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "active")
    private Active active;

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

    //수정
    public void update(String content) {
        this.content = content;
    }

    //삭제
    public void softDelete(){
        this.active = Active.INACTIVE;
    }
}
