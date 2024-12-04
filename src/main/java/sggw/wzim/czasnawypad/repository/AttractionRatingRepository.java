package sggw.wzim.czasnawypad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sggw.wzim.czasnawypad.db.entity.AttractionRating;
import sggw.wzim.czasnawypad.db.entity.User;

@Repository
public interface AttractionRatingRepository extends JpaRepository<AttractionRating, Integer> {
    List<AttractionRating> findByUser(User user);
    List<AttractionRating> findByAttractionId(Integer attractionId);
    
    @Query("SELECT r.attraction.id AS attractionId, AVG(r.rating) AS averageRating " +
           "FROM AttractionRating r " +
           "GROUP BY r.attraction.id " +
           "HAVING AVG(r.rating) >= :minRating")
    List<Object[]> findAttractionsByMinimumAverageRating(@Param("minRating") double minRating);
}
