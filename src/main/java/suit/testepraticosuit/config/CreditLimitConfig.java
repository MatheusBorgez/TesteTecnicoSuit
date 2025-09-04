package suit.testepraticosuit.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Getter
@Configuration
public class CreditLimitConfig {
    @Value("${suitpay.credit.vip-min-credit-value}")
    private BigDecimal vipMinCreditValue;
}
