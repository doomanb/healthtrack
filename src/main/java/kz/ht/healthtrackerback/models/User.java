package kz.ht.healthtrackerback.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String name;
    private String password;
    private long age;
    private long height;
    private long weight;
    private long purposeWeight;
    private long purposeWeightWeekly;
    private long purposeType;
    private long gender;
    private long generalPurposeTypeId;
    private long dietTypeId;
    private long activityLevelId;
    @Transient
    private List<ProductType> avoidingProducts;
}
