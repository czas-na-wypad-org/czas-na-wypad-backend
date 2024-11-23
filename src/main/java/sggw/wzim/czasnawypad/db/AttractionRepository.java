package sggw.wzim.czasnawypad.db;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sggw.wzim.czasnawypad.db.entity.Attraction;

import java.math.BigDecimal;
import java.util.List;

@Hidden
@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Integer> {

    List<Attraction> findAllByIsDeletedFalse();

    List<Attraction> findAllByIsDeletedFalseAndType(String type);

    List<Attraction> findAllByIsDeletedFalseAndPriceLevel(String priceLevel);

    @Query(value = """
            SELECT a.*, 
                   111111 *
                   DEGREES(ACOS(LEAST(1.0, COS(RADIANS(ST_Y(a.localization))) 
                        * COS(RADIANS(:latitude)) 
                        * COS(RADIANS(ST_X(a.localization) - :longitude)) 
                        + SIN(RADIANS(ST_Y(a.localization))) 
                        * SIN(RADIANS(:latitude))))) AS distance_in_km
            FROM attraction a
            WHERE a.is_deleted = false
            HAVING distance_in_km <= :maxDistance
            """, nativeQuery = true)
    List<Attraction> findAllWithinDistance(
            @Param("latitude") BigDecimal latitude,
            @Param("longitude") BigDecimal longitude,
            @Param("maxDistance") BigDecimal maxDistance
    );

}
