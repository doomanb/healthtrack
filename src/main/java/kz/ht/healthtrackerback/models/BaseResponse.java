package kz.ht.healthtrackerback.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse<T> {
    @Builder.Default
    private int httpStatusCode = 200;
    private String failureReason;
    @Builder.Default
    private boolean isSuccess = true;
    private T value;
}
