package kz.ht.healthtrackerback.models;

import jakarta.persistence.*;
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
@Table(name = "meal")
public class Meal {
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
    private List<Ingredient> ingredients;
}
