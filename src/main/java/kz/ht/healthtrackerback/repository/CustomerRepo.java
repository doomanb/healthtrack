package kz.ht.healthtrackerback.repository;

import kz.ht.healthtrackerback.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> countAll();
}
