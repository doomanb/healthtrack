package kz.ht.healthtrackerback.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private long id;
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
    private List<Product> avoidingProducts;
}
