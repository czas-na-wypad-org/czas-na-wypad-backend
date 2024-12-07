package sggw.wzim.czasnawypad.db;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sggw.wzim.czasnawypad.db.entity.User;

import java.util.Optional;

@Hidden
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByLogin(String login);

}
