package org.myungkeun.hiking_240423.hiking;

import lombok.RequiredArgsConstructor;
import org.myungkeun.hiking_240423.entities.Hiking;
import org.myungkeun.hiking_240423.hiking.domain.HikingInfoRes;
import org.myungkeun.hiking_240423.hiking.domain.HikingRegisterReq;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class HikingService {
    private final HikingRepository hikingRepository;

    public String registerHiking(
            HikingRegisterReq registerReq
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Hiking hiking = Hiking.builder()
                .title(registerReq.getTitle())
                .hikeStartDate(LocalDateTime.parse(registerReq.getHikeStartDate(), formatter))
                .hikeEndDate(LocalDateTime.parse(registerReq.getHikeEndDate(), formatter))
                .distance(registerReq.getDistance())
                .elevation(registerReq.getElevation())
                .estimatedDuration(registerReq.getEstimatedDuration())
                .description(registerReq.getDescription())
                .build();
        hikingRepository.save(hiking);
        return "success registered";
    }

    public HikingInfoRes getHikingInfoById(
            Long id
    ) {
        return hikingRepository.findById(id)
                .map(e -> {
                    return HikingInfoRes.builder()
                            .data(e)
                            .build();
                }).orElseThrow(
                        () -> new RuntimeException("Hiking not found")
                );
    }

    public List<HikingInfoRes> getAllHiking() {
        List<HikingInfoRes> hikingList = new ArrayList<>();
        List<Hiking> entities = hikingRepository.findAll();
        if(entities != null && entities.size() > 0) {
            hikingList = entities.stream().map(e -> {
                return HikingInfoRes.builder()
                        .data(e)
                        .build();
            }).collect(Collectors.toList());
        }
        return hikingList;
    }

}
