package kz.ht.healthtrackerback.repository;

import kz.ht.healthtrackerback.models.DayPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DayPlanRepo extends JpaRepository<DayPlan, Integer> {

    Optional<DayPlan> findByUserId(int userId);
}
