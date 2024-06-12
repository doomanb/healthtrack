package kz.ht.healthtrackerback.controller;

import kz.ht.healthtrackerback.models.*;
import kz.ht.healthtrackerback.service.basepage.BasePageService;
import kz.ht.healthtrackerback.service.mainpage.MainPageService;
import kz.ht.healthtrackerback.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "*")
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
    public List<ActivityLevel> getActivityLevels() {
        return basePageService.getActivityLevels();
    }

    @GetMapping("/generalpurposetype")
    public List<GeneralPurposeType> getGeneralPurposeTypes() {
        return basePageService.getGeneralPurposeTypes();
    }

    @GetMapping("/user")
    public BaseResponse<User> getUserByEmail(@RequestParam String email) {
        return basePageService.getUserByEmail(email);
    }


    //user-service
    @PostMapping("/user/register")
    public User register(@RequestBody UserRegistrationForm request) {
        return userService.register(request);
    }

    @PostMapping("/user/login")
    public User login(@RequestBody UserAuthForm request) {
        return userService.authorization(request).getValue();
    }

    @PostMapping("/user/updateinfo")
    public void updateInfo(@RequestBody UserUpdateForm request) {
        userService.updateUserInfo(request);
    }

    //main-page
    @GetMapping("/dayplan/day")
    public DayPlan getDayPlanForDate(@RequestParam int userId, @RequestParam String date) {
        return mainPageService.getDayPlanForDate(userId, date);
    }

    @PostMapping("/dayplan/generate")
    public DayPlan generateDayPlan(@RequestBody DayPlaneGenerateRequest request) {
        return mainPageService.generateDayPlan(request);
    }

    @GetMapping("/meal/all")
    public List<AllMeal> getAllMeals() {
        return mainPageService.getAllMeals();
    }

    @DeleteMapping("/dayplan/remove-meal")
    public DayPlan deleteMealFromPlan(@RequestParam int MealId,
                                      @RequestParam int DayPlanId,
                                      @RequestParam int PlanMealCollectionId) {
        return mainPageService.deleteMealToPlan(MealId, DayPlanId, PlanMealCollectionId);
    }

    @PostMapping("/dayplan/add-meal")
    public DayPlan addMealToPlan(@RequestBody AddMealRequest request) {
        return mainPageService.addMealToPlan(request);
    }

    @PostMapping("/user/add-products")
    public void addProductsToUser(@RequestBody AddProductsRequest request) {
        mainPageService.addProductToUser(request);
    }

    @GetMapping("/user/products")
    public List<Product> getUserProducts(@RequestParam int userId) {
        return mainPageService.getUserProducts(userId);
    }


}
