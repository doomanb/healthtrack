package kz.ht.healthtrackerback.service.user;

import kz.ht.healthtrackerback.models.UserAuthForm;
import kz.ht.healthtrackerback.models.UserRegistrationForm;
import kz.ht.healthtrackerback.models.UserUpdateForm;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    void register(UserRegistrationForm request);

    void authorization(UserAuthForm request);

    void updateUserInfo(UserUpdateForm request);
}
