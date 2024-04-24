package org.myungkeun.hiking_240423.hiking;

import org.myungkeun.hiking_240423.entities.Hiking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HikingRepository extends JpaRepository<Hiking, Long> {
}
