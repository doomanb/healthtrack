package kz.ht.healthtrackerback.service.mainpage.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.ht.healthtrackerback.models.*;
import kz.ht.healthtrackerback.repository.*;
import kz.ht.healthtrackerback.service.mainpage.MainPageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService {

    private final DayPlanRepo dayPlanRepo;
    private final ProductRepo productRepo;
    private final MealRepo mealRepo;
    private final IngredientRepo ingredientRepo;
    private final UserProductRepo userProductRepo;
    private final MealPeriodRepo mealPeriodRepo;
    private final AllMealRepo allMealRepo;
    private final AllIngredientRepo allIngredientRepo;
    private final ObjectMapper objectMapper;

    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final static List<MealPeriod> ALL_PERIOD = Arrays.asList(
            MealPeriod.builder()
                    .mealPeriodName("Завтрак")
                    .id(0)
                    .periodType(0)
                    .build(),
            MealPeriod.builder()
                    .mealPeriodName("Полдник")
                    .id(1)
                    .periodType(1)
                    .build(),
            MealPeriod.builder()
                    .mealPeriodName("Обед")
                    .id(2)
                    .periodType(2)
                    .build(),
            MealPeriod.builder()
                    .mealPeriodName("Ужин")
                    .id(3)
                    .periodType(3)
                    .build());

    @Override
    public List<AllMeal> getAllMeals() {
        val meals = allMealRepo.findAll();

        return meals.stream()
                .peek(meal -> meal.setIngredients(allIngredientRepo
                        .findAllByMealId(meal.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getUserProducts(int userId) {
        val userProductIds = userProductRepo.findAllByUserId(userId).stream()
                .map(UserProduct::getProductId)
                .collect(Collectors.toSet());

        return productRepo.findAll().stream()
                .filter(prod -> userProductIds.contains(prod.getId()))
                .collect(Collectors.toList());
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

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DayPlan deleteMealToPlan(int mealId, int dayPlanId, int planMealCollectionId) {
        mealRepo.deleteById(mealId);
        return dayPlanRepo.findById(dayPlanId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Day plan not found"));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    @SneakyThrows
    public DayPlan addMealToPlan(AddMealRequest request) {
        log.info("addMealToPlan request: " + objectMapper.writeValueAsString(request));
        val meal = allMealRepo.findById(request.getMealId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found"));
        val ingredients = allIngredientRepo.findAllByMealId(meal.getId());
        val savedIngredients = ingredientRepo.saveAll(ingredients.stream()
                .map(i -> Ingredient.builder()
                        .metricType(i.getMetricType())
                        .amount(i.getAmount())
                        .name(i.getName())
                        .imageURL(i.getImageURL())
                        .mealId(meal.getId())
                        .productId(i.getProductId())
                        .build())
                .collect(Collectors.toList()));
        val dayPlan = dayPlanRepo.findById(request.getDayPlanId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Day plan not found"));
        val mealPeriod = mealPeriodRepo.findById(request.getPlanMealCollectionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Collection not found"));
        val meals = Collections.singletonList(Meal.builder()
                .id(meal.getId())
                .mealPeriodId(mealPeriod.getId())
                .name(meal.getName())
                .recipe(meal.getRecipe())
                .imageURL(meal.getImageURL())
                .calories(meal.getCalories())
                .proteins(meal.getProteins())
                .carbohydrates(meal.getCarbohydrates())
                .fats(meal.getFats())
                .ingredients(savedIngredients)
                .build());

        mealPeriod.setMeals(Collections.singletonList(Meal.builder()
                .id(meal.getId())
                .mealPeriodId(mealPeriod.getId())
                .name(meal.getName())
                .recipe(meal.getRecipe())
                .imageURL(meal.getImageURL())
                .calories(meal.getCalories())
                .proteins(meal.getProteins())
                .carbohydrates(meal.getCarbohydrates())
                .fats(meal.getFats())
                .ingredients(savedIngredients)
                .build()));

        mealRepo.saveAll(meals);

        mealPeriod.getMeals().forEach(m -> {
            if (request.getMealId() == m.getId()) {
                m.setId(meal.getId());
                m.setMealPeriodId(mealPeriod.getId());
                m.setName(meal.getName());
                m.setRecipe(meal.getRecipe());
                m.setImageURL(meal.getImageURL());
                m.setCalories(meal.getCalories());
                m.setProteins(meal.getProteins());
                m.setCarbohydrates(meal.getCarbohydrates());
                m.setFats(meal.getFats());
                m.setIngredients(ingredients.stream()
                        .map(i -> Ingredient.builder()
                                .mealId(meal.getId())
                                .amount(i.getAmount())
                                .imageURL(i.getImageURL())
                                .mealId(i.getMealId())
                                .productId(i.getProductId())
                                .build())
                        .collect(Collectors.toList()));
            }
        });
        dayPlan.setCalories(dayPlan.getCalories() + meal.getCalories());
        dayPlan.setProteins(dayPlan.getProteins() + meal.getProteins());
        dayPlan.setCarbohydrates(dayPlan.getCarbohydrates() + meal.getCarbohydrates());
        dayPlan.setFats(dayPlan.getFats() + meal.getFats());
        dayPlan.setMealPeriods(mealPeriodRepo.findAllByDayPlanId(dayPlan.getId()));
        log.info("add mealToPlan: " + objectMapper.writeValueAsString(dayPlan));
        return getDayPlanForDate(dayPlan.getUserId(), LocalDate.now().format(DATE_FORMATTER));
    }

    @Override
    @SneakyThrows
    public DayPlan generateDayPlan(DayPlaneGenerateRequest request) {
        log.info("generateDayPlan request: " + objectMapper.writeValueAsString(request));
        val instant = Instant.parse(request.getDate());
        val zonedDateTime = instant.atZone(ZoneId.of("UTC+5"));
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        val dayPlan = dayPlanRepo.save(DayPlan.builder()
                .userId(request.getUserId())
                .date(LocalDate.parse(zonedDateTime.format(formatter), DATE_TIME_FORMATTER))
                .build());
        val mealPeriod = ALL_PERIOD.stream()
                .peek(m -> m.setDayPlanId(dayPlan.getId()))
                .collect(Collectors.toList());
        mealPeriodRepo.saveAll(mealPeriod);
        dayPlan.setMealPeriods(mealPeriod);
        log.info("generateDayPlan: " + objectMapper.writeValueAsString(dayPlan));
        return dayPlan;
    }


    @Override
    @SneakyThrows
    public DayPlan getDayPlanForDate(int userId, String date) {
        log.info("getDayPlanForDate request: " + userId + " " + date);
        val dayPlan = dayPlanRepo.findByUserIdAndDate(userId, LocalDate.parse(date, DATE_FORMATTER))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Day plan not found"));
        val mealPeriods = mealPeriodRepo.findAllByDayPlanId(dayPlan.getId());

        val mealPeriodIds = mealPeriods.stream()
                .map(MealPeriod::getId)
                .collect(Collectors.toList());

        val meals = mealRepo.findAllByMealPeriodIdIn(mealPeriodIds);
        val mealPeriodToMealsMap = meals.stream()
                .collect(Collectors.groupingBy(Meal::getMealPeriodId));
        val mealIds = meals.stream()
                .map(Meal::getId)
                .collect(Collectors.toList());

        val ingredients = allIngredientRepo.findAllByMealIdIn(mealIds);

        val mealToIngredientsMap = ingredients.stream()
                .collect(Collectors.groupingBy(AllIngredient::getMealId));

        mealPeriods.forEach(mp -> {
            val mealsForPeriod = mealPeriodToMealsMap.getOrDefault(mp.getId(), Collections.emptyList());

            mealsForPeriod.forEach(meal -> {
                val ingredientsForMeal = mealToIngredientsMap.getOrDefault(meal.getId(), Collections.emptyList()).stream()
                        .map(i -> Ingredient.builder()
                                .productId(i.getProductId())
                                .mealId(i.getMealId())
                                .metricType(i.getMetricType())
                                .name(i.getName())
                                .imageURL(i.getImageURL())
                                .amount(i.getAmount())
                                .build())
                        .collect(Collectors.toList());
                meal.setIngredients(ingredientsForMeal);
            });

            mp.setMeals(mealsForPeriod);
        });

        dayPlan.setMealPeriods(mealPeriods.stream()
                .sorted(Comparator.comparing(MealPeriod::getPeriodType))
                .collect(Collectors.toList()));
        log.info("dayPlanForDate: " + objectMapper.writeValueAsString(dayPlan));
        return dayPlan;
    }


}
