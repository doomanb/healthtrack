package kz.ht.healthtrackerback.repository;

import kz.ht.healthtrackerback.models.UserAvoidingProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAvoidingProductsRepo extends JpaRepository<UserAvoidingProducts, Integer> {
}
