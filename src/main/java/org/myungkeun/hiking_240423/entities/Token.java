package org.myungkeun.hiking_240423.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.myungkeun.hiking_240423.jwt.domain.TokenType;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    @NotNull
    private String accessToken;

    @Column(unique = true)
    @NotNull
    private String refreshToken;

    @Enumerated
    private TokenType tokenType = TokenType.TOKEN_BEARER;

    private boolean revoked;

    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
