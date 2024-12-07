package sggw.wzim.czasnawypad.repository;

import java.util.List;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sggw.wzim.czasnawypad.db.dto.AttractionAverageRatingDTO;
import sggw.wzim.czasnawypad.db.dto.AttractionRatingDTO;
import sggw.wzim.czasnawypad.db.entity.Attraction;
import sggw.wzim.czasnawypad.db.entity.AttractionRating;
import sggw.wzim.czasnawypad.db.entity.User;

@Hidden
@Repository
public interface AttractionRatingRepository extends JpaRepository<AttractionRating, Integer> {
    List<AttractionRating> findByUserId(Integer userId);
    List<AttractionRating> findByAttractionId(Integer attractionId);

    @Query("SELECT new sggw.wzim.czasnawypad.db.dto.AttractionAverageRatingDTO(r.attraction.id, r.attraction.name, AVG(r.rating) ) " +
           "FROM AttractionRating r " +
           "GROUP BY r.attraction.id, r.attraction.name " +
           "HAVING AVG(r.rating) >= :minRating")
    List<AttractionAverageRatingDTO> findAttractionsByMinimumAverageRating(@Param("minRating") double minRating);
}
