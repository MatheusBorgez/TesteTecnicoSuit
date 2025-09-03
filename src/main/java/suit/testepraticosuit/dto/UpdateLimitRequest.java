package suit.testepraticosuit.dto;

import java.math.BigDecimal;

public record UpdateLimitRequest(
        Long costumerId,
        BigDecimal newLimit
) {
}
