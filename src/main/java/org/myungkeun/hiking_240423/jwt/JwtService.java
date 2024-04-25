package org.myungkeun.hiking_240423.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.access-token.expiration}")
    private long accessExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    // Base64Url 로 인코딩된 시크릿키를 디코딩하여 키 객체 생성 후 반환
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 주오진 토큰을 파싱하여 모든 클레임 반환
    private Claims extractAllClaim(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Jwt 만료시간 추출
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 토큰 생성 로직
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiraion
    ) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiraion))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 주어진 토큰에서 특정 클레임 추출
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaim(token);
        return claimResolver.apply(claims);
    }

    // 주어진 토큰에서 email 추출
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 주어진 사용자 정보를 기반으로 리프레쉬토큰 생성
    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    // 주어진 사용자 정보를 기반으로 액세스토큰 생성
    public String generateAccessToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, accessExpiration);
    }

    // 토큰 만료 검사
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 토큰 유효성 검사
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
}
