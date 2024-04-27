package kz.ht.healthtrackerback.repository;

import kz.ht.healthtrackerback.models.DietType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietTypeRepo extends JpaRepository<DietType, Integer> {
}
