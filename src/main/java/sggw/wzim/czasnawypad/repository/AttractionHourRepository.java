package sggw.wzim.czasnawypad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sggw.wzim.czasnawypad.model.AttractionHour;
import java.util.List;

public interface AttractionHourRepository extends JpaRepository<AttractionHour, Integer> {
    List<AttractionHour> findByAttractionId(Integer attractionId);
}