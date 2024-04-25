package org.myungkeun.hiking_240423.member;

import lombok.RequiredArgsConstructor;
import org.myungkeun.hiking_240423.base.BaseResponse;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

}
