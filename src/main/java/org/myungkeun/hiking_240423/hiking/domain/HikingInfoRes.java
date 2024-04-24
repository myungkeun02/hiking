package org.myungkeun.hiking_240423.hiking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myungkeun.hiking_240423.entities.Hiking;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class HikingInfoRes {
    private Hiking data;
}
