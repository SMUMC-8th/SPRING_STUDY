package com.example.umc8th.code.article.entity;

import com.example.umc8th.code.article.dto.ArticleRequestDTO;
import com.example.umc8th.code.article.enums.Active;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.example.umc8th.code.reply.entity.Reply;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
//이걸 프라이빗으로 설정안하면 빌더말고 다른 생성자를 만들어서 쓸수도있어서 막은것
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "like_num")
    private int likeNum;

    @Enumerated(EnumType.STRING)
    @Column(name = "active")
    private Active active;

    @CreatedDate
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    //매핑은 선택 - OneToMany
    //엔티티 많아지면 extends하기 - @MappedSuperClass
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    //수정
    public void update(ArticleRequestDTO.CreateArticleDTO dto) {
        this.content = dto.getContent();
        this.title = dto.getTitle();
    }

    //삭제
    public void softDelete(){
        this.active = Active.INACTIVE;
    }

}
