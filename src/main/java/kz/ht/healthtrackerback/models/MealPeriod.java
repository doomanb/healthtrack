package kz.ht.healthtrackerback.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "meal_period")
public class MealPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int dayPlanId;
    private String mealPeriodName;
    private int periodType;
    private int calories;
    private int proteins;
    private int carbohydrates;
    private int fats;
    @Transient
    private List<Meal> meals;
}
