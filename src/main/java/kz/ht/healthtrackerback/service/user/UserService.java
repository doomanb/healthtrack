package kz.ht.healthtrackerback.service.user;

import kz.ht.healthtrackerback.models.*;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    void register(UserRegistrationForm request);

    BaseResponse<User> authorization(UserAuthForm request);

    void updateUserInfo(UserUpdateForm request);
}
