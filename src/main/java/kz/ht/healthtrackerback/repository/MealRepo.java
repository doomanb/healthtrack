package kz.ht.healthtrackerback.repository;

import kz.ht.healthtrackerback.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MealRepo extends JpaRepository<Meal, Integer> {

    List<Meal> findAllByMealPeriodIdIn(List<Integer> mealPeriodId);
}
