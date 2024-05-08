package kz.ht.healthtrackerback.service.basepage;

import kz.ht.healthtrackerback.models.*;

import java.util.List;

public interface BasePageService {

    List<DietType> getDietTypes();

    List<ProductType> getProductTypes();

    List<ActivityLevel> getActivityLevels();

    List<GeneralPurposeType> getGeneralPurposeTypes();

    BaseResponse<User> getUserByEmail(String email);
}
