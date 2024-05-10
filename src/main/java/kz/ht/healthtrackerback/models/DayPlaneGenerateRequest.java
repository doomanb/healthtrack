package kz.ht.healthtrackerback.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DayPlaneGenerateRequest {
    private String date;
    private int userId;
}
