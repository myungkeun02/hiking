package org.myungkeun.hiking_240423.member;

import lombok.RequiredArgsConstructor;
import org.myungkeun.hiking_240423.member.domain.MemberSignupReq;
import org.myungkeun.hiking_240423.member.domain.MemberSignupRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

//    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<MemberSignupRes> signupMember(
//            @RequestBody MemberSignupReq req
//    ) {
//        try {
//            MemberSignupRes response = Optional.of(req)
//                    .map(r -> memberService.signupMember(r))
//                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 회원가입 요청입니다"));
//
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberSignupRes> signupMember(
            @RequestBody MemberSignupReq req
    ) {
        try {
            // 회원 가입 요청(req)을 처리하고 응답을 받아옵니다.
            MemberSignupRes response = memberService.signupMember(req);
            // 회원 가입이 성공하면 HTTP 상태 코드 201(CREATED)와 함께 회원 가입 응답을 반환합니다.
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            // 회원 가입 과정에서 예외가 발생하면, HTTP 상태 코드 500(내부 서버 오류)을 반환합니다.
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);        }
    }
}
