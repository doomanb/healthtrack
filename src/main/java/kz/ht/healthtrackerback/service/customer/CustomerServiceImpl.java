package kz.ht.healthtrackerback.service.customer.impl;

import kz.ht.healthtrackerback.entity.CustomerEntity;
import kz.ht.healthtrackerback.models.RegistrationRequest;
import kz.ht.healthtrackerback.repository.CustomerRepo;
import kz.ht.healthtrackerback.service.customer.CustomerService;
import kz.ht.healthtrackerback.service.customer.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final ValidationService validationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void register(RegistrationRequest request) {
        validationService.validateEmail(request.getEmail());
        val hashedPassword  = bCryptPasswordEncoder.encode(request.getPassword());

        customerRepo.save(CustomerEntity.builder()
                        .firstName(request.getFirstName())
                        .middleName(request.getMiddleName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .encryptedPassword(hashedPassword)
                .build());

    }
}
