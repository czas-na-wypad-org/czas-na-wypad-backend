package sggw.wzim.czasnawypad.db;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sggw.wzim.czasnawypad.db.entity.Attraction;

import java.util.List;

@Hidden
@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Integer> {

    List<Attraction> findAllByPriceLevelAndIsDeletedFalse(String priceLevel);

    List<Attraction> findAllByTypeAndIsDeletedFalse(String type);

    @Query(value = """
                SELECT a FROM Attraction a
                WHERE a.isDeleted = false AND (
                    6371 * acos(
                        cos(radians(:latitude)) * cos(radians(a.latitude)) *
                        cos(radians(a.longitude) - radians(:longitude)) +
                        sin(radians(:latitude)) * sin(radians(a.latitude))
                    )
                ) <= :maxDistance
            """)
    List<Attraction> findAllWithinDistance(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("maxDistance") double maxDistance
    );

}
