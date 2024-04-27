package kz.ht.healthtrackerback.repository;

import kz.ht.healthtrackerback.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepo extends JpaRepository<Meal, Integer> {
}
