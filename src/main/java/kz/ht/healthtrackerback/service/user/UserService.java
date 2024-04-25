package kz.ht.healthtrackerback.service.user;

import kz.ht.healthtrackerback.models.UserRegistrationForm;

public interface UserService {
    void register(UserRegistrationForm request);
}
