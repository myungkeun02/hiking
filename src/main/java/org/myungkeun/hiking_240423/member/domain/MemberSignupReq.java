package org.myungkeun.hiking_240423.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myungkeun.hiking_240423.entities.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MemberSignupReq {
    private String username;
    private String nickname;
    private String password;
    private String birthday;
    private String mobile;
    private String email;
    private String region;
    private RoleType roleType;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .username(username)
                .nickname(nickname)
                .email(email)
                .password(passwordEncoder.encode(password))
                .birthday(birthday)
                .mobile(mobile)
                .region(region)
                .roleType(RoleType.NORMAL)
                .build();
    }
}
