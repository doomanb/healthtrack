package kz.ht.healthtrackerback.service.customer.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

    @Override
    public void validateEmail(String email) {
        val matcher = EMAIL_PATTERN.matcher(email);

        if(!matcher.matches())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректный email");

    }




}
