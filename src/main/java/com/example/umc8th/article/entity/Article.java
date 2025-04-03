package com.example.umc8th.article.entity;

import com.example.umc8th.global.entity.BaseTimeEntity;
import com.example.umc8th.reply.entity.Reply;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
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
}
