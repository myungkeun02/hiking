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

public class SignupRes {
    private Long id;
    private String nickname;
}
