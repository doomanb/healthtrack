package kz.ht.healthtrackerback.repository;

import kz.ht.healthtrackerback.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
