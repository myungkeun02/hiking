package org.myungkeun.hiking_240423.hiking;

import lombok.RequiredArgsConstructor;
import org.myungkeun.hiking_240423.base.BaseResponse;
import org.myungkeun.hiking_240423.hiking.domain.HikingInfoRes;
import org.myungkeun.hiking_240423.hiking.domain.HikingRegisterReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hiking")
@RequiredArgsConstructor

public class HikingController {
    private final HikingService hikingService;

    @PostMapping
    public ResponseEntity<BaseResponse<String>> registerHiking(
            @RequestBody HikingRegisterReq registerReq
    )  {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(BaseResponse
                            .<String>builder()
                            .statusCode(HttpStatus.CREATED.value())
                            .data(hikingService.registerHiking(registerReq))
                            .build());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<HikingInfoRes>> getHikingById(
            @PathVariable(name = "id") Long id
    ) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse
                            .<HikingInfoRes>builder()
                            .statusCode(HttpStatus.OK.value())
                            .data(hikingService.getHikingInfoById(id))
                            .build());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<HikingInfoRes>>> getAllHiking(
    ) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResponse
                            .<List<HikingInfoRes>>builder()
                            .data(hikingService.getAllHiking())
                            .build());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
