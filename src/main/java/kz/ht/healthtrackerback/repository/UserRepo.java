package kz.ht.healthtrackerback.repository;

import kz.ht.healthtrackerback.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    boolean notExistsByEmail(String email);
}
