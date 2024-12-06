package sggw.wzim.czasnawypad.db;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sggw.wzim.czasnawypad.db.entity.AttractionHour;

import java.util.List;

@Hidden
@Repository
public interface AttractionHourRepository extends JpaRepository<AttractionHour, Integer> {

    List<AttractionHour> findByAttractionId(Integer attractionId);

}
