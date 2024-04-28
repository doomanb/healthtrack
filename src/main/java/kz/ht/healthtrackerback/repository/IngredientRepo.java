package kz.ht.healthtrackerback.repository;

import kz.ht.healthtrackerback.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepo extends JpaRepository<Ingredient, Integer> {

    List<Ingredient> findAllByMealId(int mealId);
}
