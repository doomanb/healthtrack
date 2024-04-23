package kz.ht.healthtrackerback.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DayPlan {
    private int id;
    private int userId;
    private LocalDateTime date;
    private int calories;
    private int proteins;
    private int carbohydrates;
    private int fats;
    private List<MealPeriod> mealPeriods;
}
