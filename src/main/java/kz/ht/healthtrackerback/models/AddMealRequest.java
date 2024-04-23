package kz.ht.healthtrackerback.models;

import lombok.Data;

@Data
public class AddMealRequest {
    private int mealId;
    private int dayPlanId;
    private int planMealCollectionId;
}
