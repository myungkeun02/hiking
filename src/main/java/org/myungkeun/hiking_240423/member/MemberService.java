package org.myungkeun.hiking_240423.member;

import lombok.RequiredArgsConstructor;
import org.myungkeun.hiking_240423.entities.Member;
import org.myungkeun.hiking_240423.member.domain.MemberSignupReq;
import org.myungkeun.hiking_240423.member.domain.MemberSignupRes;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

//    public MemberSignupRes signupMember(MemberSignupReq req) {
//        Member member = memberRepository.save(req.toEntity(passwordEncoder));
//        return MemberSignupRes.builder()
//                .id(member.getId())
//                .nickname(member.getNickname())
//                .build();
//    }

public MemberSignupRes signupMember(MemberSignupReq req) {
    return Optional.of(req)
            .map(r -> memberRepository.save(r.toEntity(passwordEncoder)))
            .map(member -> MemberSignupRes.builder()
                    .id(member.getId())
                    .nickname(member.getNickname())
                    .build())
            .orElseThrow(() -> new RuntimeException("Failed to signup member"));
}

}
