package sggw.wzim.czasnawypad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sggw.wzim.czasnawypad.db.FavouriteAttraction;

@Repository
public interface FavouriteAttractionRepository extends JpaRepository<FavouriteAttraction, Long> {
    List<FavouriteAttraction> findByUser(User user);
    List<FavouriteAttraction> findByAttractionId(Long attractionId);

}