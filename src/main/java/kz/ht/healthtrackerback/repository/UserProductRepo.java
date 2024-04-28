package kz.ht.healthtrackerback.repository;

import kz.ht.healthtrackerback.models.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProductRepo extends JpaRepository<UserProduct, Integer> {

    List<UserProduct> findAllByUserId(int userId);
}
