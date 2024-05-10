package kz.ht.healthtrackerback.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "day_plan")
public class DayPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    @Column(name = "user_date")
    private LocalDate date;
    private int calories;
    private int proteins;
    private int carbohydrates;
    private int fats;
    @Transient
    private List<MealPeriod> mealPeriods;
}
