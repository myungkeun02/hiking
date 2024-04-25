package org.myungkeun.hiking_240423.member;

import lombok.RequiredArgsConstructor;
import org.myungkeun.hiking_240423.base.BaseResponse;
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
    public ResponseEntity<BaseResponse<MemberSignupRes>> signupMember(
            @RequestBody MemberSignupReq req
    ) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(BaseResponse.<MemberSignupRes>builder()
                            .data(memberService.signupMember(req))
                            .statusCode(HttpStatus.CREATED.value())
                            .build());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
