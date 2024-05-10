package kz.ht.healthtrackerback.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "all_ingredient")
public class AllIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int metricType;
    private int amount;
    private String name;
    private String imageURL;
    private int mealId;
    private int productId;
}
