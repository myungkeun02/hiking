package org.myungkeun.hiking_240423.hiking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class HikingRegisterReq {
    private String title;
    private String hikeStartDate;
    private String hikeEndDate;
    private Integer distance;
    private Integer estimatedDuration;
    private Integer elevation;
    private String description;
}
