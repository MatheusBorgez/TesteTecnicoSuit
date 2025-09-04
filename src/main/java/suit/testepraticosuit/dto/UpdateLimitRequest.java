package suit.testepraticosuit.dto;

import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public record UpdateLimitRequest(
        Long costumerId,
        @DecimalMin(value = "0", message = "The new credit limit cannot be a negative number.") BigDecimal newLimit
) {
}
