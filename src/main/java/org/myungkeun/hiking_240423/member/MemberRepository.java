package org.myungkeun.hiking_240423.member;

import org.myungkeun.hiking_240423.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Member> findByRefreshToken(String refreshToken);
    @Query("SELECT m.id FROM Member m WHERE m.email = :email")
    Long findIdByEmail(String email);
}