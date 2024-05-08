package kz.ht.healthtrackerback.service.basepage.impl;

import kz.ht.healthtrackerback.models.*;
import kz.ht.healthtrackerback.repository.*;
import kz.ht.healthtrackerback.service.basepage.BasePageService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BasePageServiceImpl implements BasePageService {

    private final DietTypeRepo dietTypeRepo;
    private final ProductTypesRepo productTypesRepo;
    private final ActivityLevelRepo activityLevelRepo;
    private final GeneralPurposeTypeRepo generalPurposeTypeRepo;
    private final UserRepo userRepo;

    @Override
    public List<DietType> getDietTypes() {
        return dietTypeRepo.findAll();
    }

    @Override
    public List<ProductType> getProductTypes() {
        return productTypesRepo.findAll();
    }

    @Override
    public List<ActivityLevel> getActivityLevels() {
        return activityLevelRepo.findAll();
    }

    @Override
    public List<GeneralPurposeType> getGeneralPurposeTypes() {
        return generalPurposeTypeRepo.findAll();
    }

    @Override
    public BaseResponse<User> getUserByEmail(String email) {
        val user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with email %s not found", email)));

        return BaseResponse.<User>builder()
                .value(user)
                .build();
    }

}
