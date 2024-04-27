package kz.ht.healthtrackerback.repository;

import kz.ht.healthtrackerback.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepo extends JpaRepository<Ingredient, Integer> {
}
