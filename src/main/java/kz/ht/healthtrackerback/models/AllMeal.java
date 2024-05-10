package kz.ht.healthtrackerback.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "all_meal")
public class AllMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String recipe;
    private String imageURL;
    private int calories;
    private int proteins;
    private int carbohydrates;
    private int fats;
    @Transient
    private List<AllIngredient> ingredients;
}
