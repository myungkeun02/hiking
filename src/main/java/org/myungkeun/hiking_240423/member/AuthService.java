package org.myungkeun.hiking_240423.member;

import lombok.RequiredArgsConstructor;
import org.myungkeun.hiking_240423.entities.Member;
import org.myungkeun.hiking_240423.entities.Token;
import org.myungkeun.hiking_240423.jwt.JwtService;
import org.myungkeun.hiking_240423.jwt.TokenRepository;
import org.myungkeun.hiking_240423.jwt.domain.TokenType;
import org.myungkeun.hiking_240423.member.domain.LoginReq;
import org.myungkeun.hiking_240423.member.domain.LoginRes;
import org.myungkeun.hiking_240423.member.domain.SignupReq;
import org.myungkeun.hiking_240423.member.domain.SignupRes;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.naming.AuthenticationException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public SignupRes signupMember(SignupReq req) {
        return Optional.of(req)
                .map(r -> memberRepository.save(r.toEntity(passwordEncoder)))
                .map(member -> SignupRes.builder()
                        .id(member.getId())
                        .nickname(member.getNickname())
                        .build())
                .orElseThrow(() -> new RuntimeException("Failed to signup member"));
    }

    public LoginRes loginUser(LoginReq req) {
        // 요청이 null인 경우 예외 처리
        if (req == null) {
            throw new IllegalArgumentException("Login request is null");
        }

        try {
            // 사용자 인증
            authenticateUser(req.getEmail(), req.getPassword());

            // 회원 조회
            Member member = findMemberByEmail(req.getEmail());

            // JWT 토큰 생성 및 회원 토큰 저장
            String accessToken = jwtService.generateAccessToken(member);
            String refreshToken = jwtService.generateRefreshToken(member);
            saveMemberToken(member, accessToken, refreshToken);

            // 로그인 응답 생성
            return LoginRes.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .username(member.getUsername())
                    .roleType(member.getRoleType())
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (NoSuchElementException e) {
            // 사용자를 찾지 못한 경우 예외 처리
            throw new RuntimeException("User not found", e);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    private void authenticateUser(String email, String password) throws AuthenticationException {
        // 사용자 인증
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }

    private Member findMemberByEmail(String email) {
        // 회원 조회
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }


    private void saveMemberToken(Member member, String accessToken, String refreshToken) {
        var token = Token.builder()
                .member(member)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(TokenType.TOKEN_BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
