package kz.ht.healthtrackerback.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DayPlaneGenerateRequest {
    private LocalDateTime date;
    private long userId;
}
