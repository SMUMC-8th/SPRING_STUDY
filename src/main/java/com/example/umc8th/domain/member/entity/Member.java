package com.example.umc8th.domain.member.entity;

import com.example.umc8th.domain.member.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "access_token", length = 1000)
    private String accessToken;

    @Column(name = "refresh_token", length = 1000)
    private String refreshToken;

//    좋은 대안: RefreshToken 전용 저장소 사용
//    Redis 사용 (추천)
//    refreshToken은 Redis에 저장하고, 키는 userId, 값은 refreshToken으로 설정
//      → 빠른 속도, TTL 설정, 자동 만료 가능
//
//    DB에 별도 테이블 구성
//
//    예: RefreshTokenEntity(userId, token, expiryDate)
//
//    사용자 인증과 도메인 로직을 분리 가능
//
//    실무에서 일반적인 구조
//    accessToken: 클라이언트에만 저장 (DB 저장 X)
//    refreshToken: 서버(예: Redis)에 저장 (로그아웃 시 삭제)
//    Member: 사용자 정보만 가짐 (이메일, 이름, 권한 등)

}
