package kz.ht.healthtrackerback.controller;

import kz.ht.healthtrackerback.models.*;
import kz.ht.healthtrackerback.service.basepage.BasePageService;
import kz.ht.healthtrackerback.service.mainpage.MainPageService;
import kz.ht.healthtrackerback.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/api")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class MainPageController {

    private final UserService userService;
    private final BasePageService basePageService;
    private final MainPageService mainPageService;

    //pre-register-page
    @GetMapping("/diettype")
    public BaseResponse<DietType> getDietTypes() {
        return null;
    }

    @GetMapping("/producttype")
    public BaseResponse<ProductType> getProductTypes() {
        return null;
    }

    @GetMapping("/activitylevel")
    public BaseResponse<ActivityLevel> getActivityLevels() {
        return null;
    }

    @GetMapping("/generalpurposetype")
    public BaseResponse<GeneralPurposeType> getGeneralPurpostTypes() {
        return null;
    }

    @GetMapping("/user")
    public BaseResponse<User> getUserByEmail(@RequestParam("email") String email) {
        return null;
    }


    //user-service
    @PostMapping("/user/register")
    public void register(@RequestBody UserRegistrationForm request) {

    }

    @PostMapping("/user/login")
    public void login(@RequestBody UserAuthForm request) {

    }

    @PostMapping("/user/updateinfo")
    public void updateInfo(@RequestBody UserUpdateForm request) {

    }

    //main-page
    @GetMapping("/dayplan/day")
    public BaseResponse<DayPlan> getDayPlanForDate() {
        return null;
    }

    @PostMapping("/dayplan/generate")
    public void postGenerateDayPlan(@RequestBody DayPlaneGenerateRequest request) {

    }

    @GetMapping("/meal/all")
    public BaseResponse<Meal> getAllMeals() {
        return null;
    }

    @DeleteMapping("/dayplan/remove-meal")
    public void deleteMealFromPlan(@RequestParam int MealId, @RequestParam int DayPlanId, @RequestParam int PlanMealCollectionId) {

    }

    @PostMapping("/dayplan/add-meal")
    public void addMealToPlan(@RequestBody AddMealRequest request) {

    }

    @PostMapping("/user/add-products")
    public void addProductsToUser(@RequestBody AddProductsRequest request) {

    }

    @GetMapping("/user/products")
    public BaseResponse<Product> getUserProducts(@RequestParam long userId) {
        return null;
    }


}
