package com.example.umc8th.domain.member.repository;

import com.example.umc8th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByUsername(String username);
}
