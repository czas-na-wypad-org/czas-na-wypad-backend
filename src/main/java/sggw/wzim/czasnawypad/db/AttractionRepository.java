package sggw.wzim.czasnawypad.db;

import io.swagger.v3.oas.annotations.Hidden;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sggw.wzim.czasnawypad.db.entity.Attraction;

import java.util.List;

@Hidden
@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Integer> {

    List<Attraction> findAllByIsDeletedFalse();

    List<Attraction> findAllByIsDeletedFalseAndType(String type);

    List<Attraction> findAllByIsDeletedFalseAndPriceLevel(String priceLevel);

    @Query("SELECT a FROM Attraction a " +
            "WHERE a.isDeleted = false " +
            "AND ST_Distance(a.localization, :point) <= :maxDistance * 1000")
    List<Attraction> findAllWithinDistanceAndNotDeleted(
            @Param("point") Point point,
            @Param("maxDistance") double maxDistance
    );


    @Query(value = """
            SELECT a, 
                   (
                       6371 * acos(
                           cos(radians(:latitude)) * cos(radians(CAST(ST_Y(a.localization) AS double))) *
                           cos(radians(CAST(ST_X(a.localization) AS double)) - radians(:longitude)) +
                           sin(radians(:latitude)) * sin(radians(CAST(ST_Y(a.localization) AS double)))
                       )
                   ) AS distance
            FROM Attraction a
            WHERE a.isDeleted = false
            """)
    List<Object[]> findAttractionsWithDistance(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude
    );


}
