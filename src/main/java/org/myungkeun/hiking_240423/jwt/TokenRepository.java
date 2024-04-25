package org.myungkeun.hiking_240423.jwt;

import org.myungkeun.hiking_240423.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findAllValidTokenByMember(Long id);
    Optional<Token> findByAccessToken(String token);
}
