package kz.ht.healthtrackerback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static kz.ht.healthtrackerback.models.Tables.CUSTOMERS;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = CUSTOMERS)
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String encryptedPassword;
    private String firstName;
    private String middleName;
    private String lastName;
    private String country;
    private LocalDateTime createdAt;
}
