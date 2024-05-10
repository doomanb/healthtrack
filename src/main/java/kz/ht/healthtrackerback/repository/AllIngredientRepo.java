package kz.ht.healthtrackerback.repository;

import kz.ht.healthtrackerback.models.AllIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllIngredientRepo extends JpaRepository<AllIngredient, Integer> {

    List<AllIngredient> findAllByMealId(int mealId);
}
