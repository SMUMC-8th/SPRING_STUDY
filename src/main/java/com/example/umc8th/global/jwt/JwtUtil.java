package com.example.umc8th.global.jwt;

import com.example.umc8th.code.member.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final Duration accessExpiration;
    private final Duration refreshExpiration;

    public JwtUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.time.access-expiration}") long accessExpiration,
                   @Value("${jwt.time.refresh-expiration}") long refreshExpiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpiration = Duration.ofMillis(accessExpiration);
        this.refreshExpiration = Duration.ofMillis(refreshExpiration);
    }

    public String createAccessToken(Member member) {
        return createToken(member, accessExpiration);
    }

    public String createRefreshToken(Member member) {
        return createToken(member, refreshExpiration);
    }

    public String getUsername(String token) {
        try {
            return getClaims(token).getPayload().getSubject(); // Parsing해서 Subject 가져오기
        } catch (JwtException e) {
            return null;
        }
    }

    public boolean isValid(String token) { // 유효한지 확인 후 true, false 반환
        try {
            getClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private String createToken(Member member, Duration expiration) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(member.getUsername()) // Subject를 Username으로 설정
                .claim("id", member.getId()) // 페이로드에 추가하는 것
                .issuedAt(Date.from(now)) // 언제 발급한지
                .expiration(Date.from(now.plus(expiration))) // 언제까지 유효한지
                .signWith(secretKey) // sign할 Key
                .compact();
    }

    private Jws<Claims> getClaims(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(60)
                .build()
                .parseSignedClaims(token);
    }
}
