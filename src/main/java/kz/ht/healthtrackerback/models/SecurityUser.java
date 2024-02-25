package kz.ht.healthtrackerback.models;

import kz.ht.healthtrackerback.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails {
    private CustomerEntity customer;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return customer.getRole().getAuthorities();
    }

    @Override
    public String getPassword() {
        return customer.getEncryptedPassword();
    }

    @Override
    public String getUsername() {
        return customer.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return customer.getFirstName() + " " + customer.getLastName();
    }

}