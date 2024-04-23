package kz.ht.healthtrackerback.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MealPeriod {
    private int id;
    private int dayPlanId;
    private String mealPeriodName;
    private int periodType;
    private int calories;
    private int proteins;
    private int carbohydrates;
    private int fats;
    private List<Meal> meals;
}
