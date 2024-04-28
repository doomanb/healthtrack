package kz.ht.healthtrackerback.service.mainpage.impl;

import kz.ht.healthtrackerback.models.*;
import kz.ht.healthtrackerback.repository.*;
import kz.ht.healthtrackerback.service.mainpage.MainPageService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService {

    private final DayPlanRepo dayPlanRepo;
    private final ProductRepo productRepo;
    private final MealRepo mealRepo;
    private final IngredientRepo ingredientRepo;
    private final UserProductRepo userProductRepo;

    public BaseResponse<DayPlan> getDayPlan(int userId, LocalDateTime date) {

    }

    @Override
    public BaseResponse<List<Meal>> getAllMeals() {
        val meals = mealRepo.findAll();

        val mealsWithIngredients = meals.stream()
                .peek(meal -> meal.setIngredients(ingredientRepo
                        .findAllByMealId(meal.getId())))
                .collect(Collectors.toList());

        return BaseResponse.<List<Meal>>builder()
                .value(mealsWithIngredients)
                .build();

    }

    @Override
    public BaseResponse<List<Product>> getUserProducts(int userId) {
        val userProductIds = userProductRepo.findAllByUserId(userId).stream()
                .map(UserProduct::getProductId)
                .collect(Collectors.toSet());

        val filteredProducts = productRepo.findAll().stream()
                .filter(prod -> userProductIds.contains(prod.getId()))
                .collect(Collectors.toList());

        return BaseResponse.<List<Product>>builder()
                .value(filteredProducts)
                .build();
    }

    @Override
    public void addProductToUser(AddProductsRequest request) {
        val productIds = request.getProductIds().stream()
                .map(prodId -> UserProduct.builder()
                        .userId(request.getUserId())
                        .productId(prodId)
                        .build())
                .collect(Collectors.toList());
        userProductRepo.saveAll(productIds);
    }


}
