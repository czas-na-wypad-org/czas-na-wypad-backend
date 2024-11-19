package sggw.wzim.czasnawypad.db;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sggw.wzim.czasnawypad.db.entity.Attraction;

@Hidden
@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Integer> {

}



