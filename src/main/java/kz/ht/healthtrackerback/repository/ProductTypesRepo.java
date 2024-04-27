package kz.ht.healthtrackerback.repository;

import kz.ht.healthtrackerback.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypesRepo extends JpaRepository<ProductType, Integer> {
}
