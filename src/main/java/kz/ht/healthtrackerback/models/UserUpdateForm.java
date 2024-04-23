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
public class UserUpdateForm {
    private int userId;
    private int dietTypeId;
    private List<Integer> avoidingProductIds;
    private int purposeType;
    private int generalPurposeTypeId;
    private int purposeWeight;
    private int purposeWeightWeekly;
    private int gender;
    private int height;
    private int weight;
    private int age;
    private int activityLevelId;
}
