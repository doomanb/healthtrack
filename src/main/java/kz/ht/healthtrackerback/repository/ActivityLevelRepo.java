package kz.ht.healthtrackerback.repository;

import kz.ht.healthtrackerback.models.ActivityLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLevelRepo extends JpaRepository<ActivityLevel, Integer> {
}
