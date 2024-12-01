package sggw.wzim.czasnawypad.adapter;

import sggw.wzim.czasnawypad.repository.UserRepository;
import sggw.wzim.czasnawypad.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlUserRepository extends UserRepository, JpaRepository<User, Integer> {
}
