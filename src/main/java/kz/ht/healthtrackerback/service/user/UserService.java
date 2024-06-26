package kz.ht.healthtrackerback.service.user;

import kz.ht.healthtrackerback.models.*;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    User register(UserRegistrationForm request);

    BaseResponse<User> authorization(UserAuthForm request);

    User updateUserInfo(UserUpdateForm request);
}
