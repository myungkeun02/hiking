package org.myungkeun.hiking_240423.hiking;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<String> registerHiking(
            @RequestBody HikingRegisterReq registerReq
    ) {
        String result = hikingService.registerHiking(registerReq);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HikingInfoRes> getHikingById(
            @PathVariable(name = "id") Long id
    ) {
        HikingInfoRes result = hikingService.getHikingInfoById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<HikingInfoRes>> getAllHiking() {
        List<HikingInfoRes> result = hikingService.getAllHiking();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
