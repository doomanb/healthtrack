package kz.ht.healthtrackerback.service.user.impl;


import kz.ht.healthtrackerback.models.*;
import kz.ht.healthtrackerback.repository.UserAvoidingProductsRepo;
import kz.ht.healthtrackerback.repository.UserRepo;
import kz.ht.healthtrackerback.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserAvoidingProductsRepo userAvoidingProductsRepo;
    private final PasswordEncoder passwordEncoder;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void register(UserRegistrationForm request) {
        validateRegistrationRequest(request);
        val userId = userRepo.save(User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .dietTypeId(request.getDietTypeId())
                .purposeType(request.getPurposeType())
                .generalPurposeTypeId(request.getGeneralPurposeTypeId())
                .purposeWeight(request.getPurposeWeight())
                .purposeWeightWeekly(request.getPurposeWeightWeekly())
                .gender(request.getGender())
                .height(request.getHeight())
                .weight(request.getWeight())
                .age(request.getAge())
                .activityLevelId(request.getActivityLevelId())
                .build()).getId();
        saveAvoidingProducts(userId, request.getAvoidingProductIds());

    }

    @Override
    public BaseResponse<User> authorization(UserAuthForm request) {
        val account = userRepo.findByEmail(request.getEmail());
        if (!account.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        account.ifPresent(auth -> {
            if (!passwordEncoder.matches(request.getPassword(), auth.getPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect password");
            }
        });

        return BaseResponse.<User>builder()
                .value(account.get())
                .build();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateUserInfo(UserUpdateForm request) {
        val oldUserInfo = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id %s not found", request.getUserId())));

        oldUserInfo.setDietTypeId(request.getDietTypeId());
        oldUserInfo.setPurposeType(request.getPurposeType());
        oldUserInfo.setGeneralPurposeTypeId(request.getGeneralPurposeTypeId());
        oldUserInfo.setPurposeType(request.getPurposeType());
        oldUserInfo.setGeneralPurposeTypeId(request.getGeneralPurposeTypeId());
        oldUserInfo.setPurposeWeight(request.getPurposeWeight());
        oldUserInfo.setPurposeWeightWeekly(request.getPurposeWeightWeekly());
        oldUserInfo.setGender(request.getGender());
        oldUserInfo.setHeight(request.getHeight());
        oldUserInfo.setWeight(request.getWeight());
        oldUserInfo.setAge(request.getAge());
        oldUserInfo.setActivityLevelId(request.getActivityLevelId());

        saveAvoidingProducts(request.getUserId(), request.getAvoidingProductIds());

        userRepo.save(oldUserInfo);
    }

    private void saveAvoidingProducts(int userId, List<Integer> avoidingProductIds) {
        avoidingProductIds.forEach(productId -> userAvoidingProductsRepo.save(UserAvoidingProducts.builder()
                .avoidingProductId(productId)
                .userId(userId)
                .build()));
    }


    private void validateRegistrationRequest(UserRegistrationForm request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords does not match");
        }

        if (userRepo.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Email %s already exists", request.getEmail()));
        }
    }

}
