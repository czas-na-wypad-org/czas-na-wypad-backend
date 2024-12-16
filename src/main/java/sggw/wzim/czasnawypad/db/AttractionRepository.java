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
    List<Attraction> findAllByIsDeletedFalseAndWithingMaxDistance(
            @Param("latitude") BigDecimal latitude,
            @Param("longitude") BigDecimal longitude,
            @Param("maxDistance") BigDecimal maxDistance);

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
            AND a.type = :type
            HAVING distance_in_km <= :maxDistance
            """, nativeQuery = true)
    List<Attraction> findAllByIsDeletedFalseAndType(@Param("type") String type,
                                                    @Param("latitude") BigDecimal latitude,
                                                    @Param("longitude") BigDecimal longitude,
                                                    @Param("maxDistance") BigDecimal maxDistance);

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
            AND a.price_level = :priceLevel
            HAVING distance_in_km <= :maxDistance
            """, nativeQuery = true)
    List<Attraction> findAllByIsDeletedFalseAndPriceLevel(@Param("priceLevel") String priceLevel,
                                                          @Param("latitude") BigDecimal latitude,
                                                          @Param("longitude") BigDecimal longitude,
                                                          @Param("maxDistance") BigDecimal maxDistance);

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
            AND a.price_level = :priceLevel
            AND a.type = :type
            HAVING distance_in_km <= :maxDistance
            """, nativeQuery = true)
    List<Attraction> findAllByIsDeletedFalseAndPriceLevelAndType(@Param("priceLevel") String priceLevel,
                                                                 @Param("type") String type,
                                                                 @Param("latitude") BigDecimal latitude,
                                                                 @Param("longitude") BigDecimal longitude,
                                                                 @Param("maxDistance") BigDecimal maxDistance);

    @Query(value = """
        SELECT a.*, 
               111111 *
               DEGREES(ACOS(LEAST(1.0, COS(RADIANS(ST_Y(a.localization))) 
                    * COS(RADIANS(:latitude)) 
                    * COS(RADIANS(ST_X(a.localization) - :longitude)) 
                    + SIN(RADIANS(ST_Y(a.localization))) 
                    * SIN(RADIANS(:latitude))))) AS distance_in_km
        FROM attraction a
        JOIN (SELECT r.attraction_id, AVG(r.rating) as avg_rating 
              FROM attraction_ratings r 
              GROUP BY r.attraction_id 
              HAVING AVG(r.rating) >= :minRating) avg_ratings
        ON a.id = avg_ratings.attraction_id
        WHERE a.is_deleted = false
        HAVING distance_in_km <= :maxDistance
        """, nativeQuery = true)
    List<Attraction> findAllByIsDeletedFalseAndWithingMaxDistanceAndMinRating(
            @Param("latitude") BigDecimal latitude,
            @Param("longitude") BigDecimal longitude,
            @Param("maxDistance") BigDecimal maxDistance,
            @Param("minRating") BigDecimal minRating);

    @Query(value = """
        SELECT a.*, 
               111111 *
               DEGREES(ACOS(LEAST(1.0, COS(RADIANS(ST_Y(a.localization))) 
                    * COS(RADIANS(:latitude)) 
                    * COS(RADIANS(ST_X(a.localization) - :longitude)) 
                    + SIN(RADIANS(ST_Y(a.localization))) 
                    * SIN(RADIANS(:latitude))))) AS distance_in_km
        FROM attraction a
        JOIN (SELECT r.attraction_id, AVG(r.rating) as avg_rating 
              FROM attraction_ratings r 
              GROUP BY r.attraction_id 
              HAVING AVG(r.rating) >= :minRating) avg_ratings
        ON a.id = avg_ratings.attraction_id
        WHERE a.is_deleted = false
        AND a.type = :type
        HAVING distance_in_km <= :maxDistance
        """, nativeQuery = true)
    List<Attraction> findAllByIsDeletedFalseAndTypeAndMinRating(
            @Param("type") String type,
            @Param("latitude") BigDecimal latitude,
            @Param("longitude") BigDecimal longitude,
            @Param("maxDistance") BigDecimal maxDistance,
            @Param("minRating") BigDecimal minRating);

    @Query(value = """
        SELECT a.*, 
               111111 *
               DEGREES(ACOS(LEAST(1.0, COS(RADIANS(ST_Y(a.localization))) 
                    * COS(RADIANS(:latitude)) 
                    * COS(RADIANS(ST_X(a.localization) - :longitude)) 
                    + SIN(RADIANS(ST_Y(a.localization))) 
                    * SIN(RADIANS(:latitude))))) AS distance_in_km
        FROM attraction a
        JOIN (SELECT r.attraction_id, AVG(r.rating) as avg_rating 
              FROM attraction_ratings r 
              GROUP BY r.attraction_id 
              HAVING AVG(r.rating) >= :minRating) avg_ratings
        ON a.id = avg_ratings.attraction_id
        WHERE a.is_deleted = false
        AND a.price_level = :priceLevel
        HAVING distance_in_km <= :maxDistance
        """, nativeQuery = true)
    List<Attraction> findAllByIsDeletedFalseAndPriceLevelAndMinRating(
            @Param("priceLevel") String priceLevel,
            @Param("latitude") BigDecimal latitude,
            @Param("longitude") BigDecimal longitude,
            @Param("maxDistance") BigDecimal maxDistance,
            @Param("minRating") BigDecimal minRating);

    @Query(value = """
        SELECT a.*, 
               111111 *
               DEGREES(ACOS(LEAST(1.0, COS(RADIANS(ST_Y(a.localization))) 
                    * COS(RADIANS(:latitude)) 
                    * COS(RADIANS(ST_X(a.localization) - :longitude)) 
                    + SIN(RADIANS(ST_Y(a.localization))) 
                    * SIN(RADIANS(:latitude))))) AS distance_in_km
        FROM attraction a
        JOIN (SELECT r.attraction_id, AVG(r.rating) as avg_rating 
              FROM attraction_ratings r 
              GROUP BY r.attraction_id 
              HAVING AVG(r.rating) >= :minRating) avg_ratings
        ON a.id = avg_ratings.attraction_id
        WHERE a.is_deleted = false
        AND a.price_level = :priceLevel
        AND a.type = :type
        HAVING distance_in_km <= :maxDistance
        """, nativeQuery = true)
    List<Attraction> findAllByIsDeletedFalseAndPriceLevelAndTypeAndMinRating(
            @Param("priceLevel") String priceLevel,
            @Param("type") String type,
            @Param("latitude") BigDecimal latitude,
            @Param("longitude") BigDecimal longitude,
            @Param("maxDistance") BigDecimal maxDistance,
            @Param("minRating") BigDecimal minRating);

}
