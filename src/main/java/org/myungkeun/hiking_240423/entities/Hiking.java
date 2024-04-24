package org.myungkeun.hiking_240423.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hiking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private LocalDateTime hikeStartDate;

    @Column
    private LocalDateTime hikeEndDate;

    @Column
    private Integer distance;

    @Column
    private Integer estimatedDuration;

    @Column
    private Integer elevation;

    @Column
    private String description;
}
