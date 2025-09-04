package suit.testepraticosuit;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import suit.testepraticosuit.domain.Costumer;
import suit.testepraticosuit.repository.CostumerLimitHistoryRepository;
import suit.testepraticosuit.repository.CostumerRepository;
import suit.testepraticosuit.service.CreditLimitService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CreditLimitServiceTest {

    @Mock
    private CostumerLimitHistoryRepository costumerLimitHistoryRepository;

    @Mock
    private CostumerRepository costumerRepository;

    @InjectMocks
    private CreditLimitService creditLimitService;

    @Test
    void shouldUpdateLimitSuccessfully() {
        Costumer costumer = new Costumer();
        costumer.setId(1L);
        costumer.setCreditLimit(BigDecimal.valueOf(1000));

        when(costumerRepository.findById(1L)).thenReturn(Optional.of(costumer));
        creditLimitService.updateCreditLimit(1L, BigDecimal.valueOf(2000), "admin");

        verify(costumerRepository).save(costumer);
        verify(costumerLimitHistoryRepository).save(any());
    }

    @Test
    void updateLimitAuthFailure() {

    }

    @Test
    void updateCostumerNotFound() {

    }

}
