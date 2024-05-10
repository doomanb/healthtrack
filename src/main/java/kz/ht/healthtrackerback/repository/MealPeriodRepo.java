package kz.ht.healthtrackerback.repository;

import kz.ht.healthtrackerback.models.MealPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MealPeriodRepo extends JpaRepository<MealPeriod, Integer> {

    List<MealPeriod> findAllByDayPlanId(int dayPlanId);
}
