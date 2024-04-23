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
public class Meal {
    private int id;
    private String name;
    private String recipe;
    private String imageURL;
    private int calories;
    private int proteins;
    private int carbohydrates;
    private int fats;
    private List<Ingredient> ingredients;
}
