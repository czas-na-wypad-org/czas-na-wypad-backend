package sggw.wzim.czasnawypad.db;

import java.util.List;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sggw.wzim.czasnawypad.db.dto.AttractionAverageRatingDTO;
import sggw.wzim.czasnawypad.db.entity.AttractionRating;

@Hidden
@Repository
public interface AttractionRatingRepository extends JpaRepository<AttractionRating, Integer> {
    List<AttractionRating> findByUserId(Integer userId);
    List<AttractionRating> findByAttractionId(Integer attractionId);

}
