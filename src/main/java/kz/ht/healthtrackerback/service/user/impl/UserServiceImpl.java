package kz.ht.healthtrackerback.service.user.impl;


import kz.ht.healthtrackerback.models.UserRegistrationForm;
import kz.ht.healthtrackerback.repository.UserRepo;
import kz.ht.healthtrackerback.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void register(UserRegistrationForm request) {
        if(request.getPassword().equals(request.getConfirmPassword()) && userRepo.notExistsByEmail(request.getEmail())) {

        } else
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Validation error");
    }

}
