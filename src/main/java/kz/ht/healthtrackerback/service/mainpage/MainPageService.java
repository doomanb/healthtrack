package kz.ht.healthtrackerback.service.mainpage;

import kz.ht.healthtrackerback.models.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

public interface MainPageService {

    List<AllMeal> getAllMeals();

    List<Product> getUserProducts(int userId);

    void addProductToUser(AddProductsRequest request);

    DayPlan addMealToPlan(AddMealRequest request);

    DayPlan deleteMealToPlan(int MealId, int DayPlanId, int PlanMealCollectionId);

    DayPlan generateDayPlan(DayPlaneGenerateRequest request);

    DayPlan getDayPlanForDate(int userId, String date);
}
