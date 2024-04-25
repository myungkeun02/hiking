package org.myungkeun.hiking_240423.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myungkeun.hiking_240423.entities.Member;

import javax.swing.plaf.BorderUIResource;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class LoginRes {
    private Long id;
    private String username;
    private String email;
    private RoleType roleType;
    private String accessToken;
    private String refreshToken;
}
