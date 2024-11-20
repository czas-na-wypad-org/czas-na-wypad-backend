package sggw.wzim.czasnawypad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sggw.wzim.czasnawypad.model.AttractionRating;

@Repository
public interface AttractionRatingRepository extends JpaRepository<AttractionRating, Long> {
    List<AttractionRating> findByUser(User user);
    List<AttractionRating> findByAttractionId(Long attractionId);
}
