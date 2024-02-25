package kz.ht.healthtrackerback.service.user.impl;

import kz.ht.healthtrackerback.models.SecurityUser;
import kz.ht.healthtrackerback.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final CustomerRepo customerRepo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = customerRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new SecurityUser(user);
    }
}
