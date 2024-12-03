package sggw.wzim.czasnawypad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sggw.wzim.czasnawypad.model.AttractionHour;

import java.util.List;

@Repository
public interface AttractionHourRepository extends JpaRepository<AttractionHour, Long> {
    List<AttractionHour> findByAttractionId(Long attractionId);
}
