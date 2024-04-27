package kz.ht.healthtrackerback.controller;

import kz.ht.healthtrackerback.models.*;
import kz.ht.healthtrackerback.service.basepage.BasePageService;
import kz.ht.healthtrackerback.service.mainpage.MainPageService;
import kz.ht.healthtrackerback.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class MainPageController {

    private final UserService userService;
    private final BasePageService basePageService;
    private final MainPageService mainPageService;

    //pre-register-page
    @GetMapping("/diettype")
    public List<DietType> getDietTypes() {
        return basePageService.getDietTypes();
    }

    @GetMapping("/producttype")
    public List<ProductType> getProductTypes() {
        return basePageService.getProductTypes();
    }

    @GetMapping("/activitylevel")
    public BaseResponse<List<ActivityLevel>> getActivityLevels() {
        return basePageService.getActivityLevels();
    }

    @GetMapping("/generalpurposetype")
    public BaseResponse<List<GeneralPurposeType>> getGeneralPurposeTypes() {
        return basePageService.getGeneralPurposeTypes();
    }

    @GetMapping("/user")
    public BaseResponse<User> getUserByEmail(@RequestParam String email) {
        return basePageService.getUserByEmail(email);
    }


    //user-service
    @PostMapping("/user/register")
    public void register(@RequestBody UserRegistrationForm request) {
        userService.register(request);
    }

    @PostMapping("/user/login")
    public void login(@RequestBody UserAuthForm request) {
        userService.authorization(request);
    }

    @PostMapping("/user/updateinfo")
    public void updateInfo(@RequestBody UserUpdateForm request) {
        userService.updateUserInfo(request);
    }

    //main-page
    @GetMapping("/dayplan/day")
    public BaseResponse<DayPlan> getDayPlanForDate(@RequestParam int userId) {
        return null;
    }

    @PostMapping("/dayplan/generate")
    public void generateDayPlan(@RequestBody DayPlaneGenerateRequest request) {

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
