package suit.testepraticosuit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import suit.testepraticosuit.repository.CostumerRepository;
import suit.testepraticosuit.service.CreditLimitService;

@SpringBootTest
@ActiveProfiles("test")
public class CreditLimitServiceTest {

    @Autowired
    private CreditLimitService creditLimitService;

    @Autowired
    private CostumerRepository costumer;

    @Test
    void shouldUpdateLimitSuccessfully() {

    }

    @Test
    void updateLimitAuthFailure() {

    }

    @Test
    void updateCostumerNotFound() {

    }

}
