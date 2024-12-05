package sggw.wzim.czasnawypad.db;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sggw.wzim.czasnawypad.db.entity.FavouriteAttraction;
import sggw.wzim.czasnawypad.db.entity.User;

import java.util.List;

@Hidden
@Repository
public interface FavouriteAttractionRepository extends JpaRepository<FavouriteAttraction, Integer> {
    List<FavouriteAttraction> findByUserId(Integer userId);
    List<FavouriteAttraction> findByAttractionId(Integer attractionId);


}
