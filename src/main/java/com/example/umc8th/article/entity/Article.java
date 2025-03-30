package com.example.umc8th.article.entity;

import com.example.umc8th.global.Entity.BaseEntity;
import com.example.umc8th.reply.entity.Reply;
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

    @Column
    private String title;
    private String content;
    private int likeNum;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Reply> replies;
}
