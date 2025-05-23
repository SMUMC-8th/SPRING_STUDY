package com.example.umc8th.domain.member.entity;

import com.example.umc8th.domain.member.enums.SocialType;
import com.example.umc8th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "thumbnail_image")
    private String thumbnailImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    private SocialType socialType;

    public void update(String profileImage, String thumbnailImage) {
        this.profileImage = profileImage;
        this.thumbnailImage = thumbnailImage;
    }
}
