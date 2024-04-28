package kz.ht.healthtrackerback.service.mainpage;

import kz.ht.healthtrackerback.models.AddProductsRequest;
import kz.ht.healthtrackerback.models.BaseResponse;
import kz.ht.healthtrackerback.models.Meal;
import kz.ht.healthtrackerback.models.Product;

import java.util.List;

public interface MainPageService {

    BaseResponse<List<Meal>> getAllMeals();

    BaseResponse<List<Product>> getUserProducts(int userId);

    void addProductToUser(AddProductsRequest request);
}
