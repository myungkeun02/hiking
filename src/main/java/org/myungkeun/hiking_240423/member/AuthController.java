package org.myungkeun.hiking_240423.member;

import lombok.RequiredArgsConstructor;
import org.myungkeun.hiking_240423.base.BaseResponse;
import org.myungkeun.hiking_240423.member.domain.LoginReq;
import org.myungkeun.hiking_240423.member.domain.LoginRes;
import org.myungkeun.hiking_240423.member.domain.SignupReq;
import org.myungkeun.hiking_240423.member.domain.SignupRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<SignupRes>> signupMember(
            @RequestBody SignupReq req
    ) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(BaseResponse.<SignupRes>builder()
                            .data(authService.signupMember(req))
                            .statusCode(HttpStatus.CREATED.value())
                            .build());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<LoginRes>> loginMember(
            @RequestBody LoginReq req
    ) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse.<LoginRes>builder()
                            .data(authService.loginUser(req))
                            .statusCode(HttpStatus.OK.value())
                            .build());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
