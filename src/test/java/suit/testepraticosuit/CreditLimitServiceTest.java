package suit.testepraticosuit;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import suit.testepraticosuit.config.CreditLimitConfig;
import suit.testepraticosuit.domain.Costumer;
import suit.testepraticosuit.repository.CreditLimitHistoryRepository;
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
    private CreditLimitHistoryRepository creditLimitHistoryRepository;

    @Mock
    private CostumerRepository costumerRepository;

    @Mock
    private CreditLimitConfig creditLimitConfig;

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
        verify(creditLimitHistoryRepository).save(any());
    }

    @Test
    void shouldUpdateHigherVipLimitSuccessfully() {
        Costumer costumer = new Costumer();
        costumer.setId(1L);
        costumer.setCreditLimit(BigDecimal.valueOf(6000));
        costumer.setVip(true);

        when(creditLimitConfig.getVipMinCreditValue()).thenReturn(BigDecimal.valueOf(5000));
        when(costumerRepository.findById(1L)).thenReturn(Optional.of(costumer));
        creditLimitService.updateCreditLimit(1L, BigDecimal.valueOf(10000), "admin");

        verify(costumerRepository).save(costumer);
        verify(creditLimitHistoryRepository).save(any());
    }

    @Test
    void shouldUpdateEqualVipLimitSuccessfully() {
        Costumer costumer = new Costumer();
        costumer.setId(1L);
        costumer.setCreditLimit(BigDecimal.valueOf(10000));
        costumer.setVip(true);

        when(creditLimitConfig.getVipMinCreditValue()).thenReturn(BigDecimal.valueOf(5000));
        when(costumerRepository.findById(1L)).thenReturn(Optional.of(costumer));
        creditLimitService.updateCreditLimit(1L, BigDecimal.valueOf(5000), "admin");

        verify(costumerRepository).save(costumer);
        verify(creditLimitHistoryRepository).save(any());
    }

    @Test
    void updateCostumerNotFound() {
        when(costumerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () ->
                creditLimitService.updateCreditLimit(1L, BigDecimal.valueOf(2000), "admin")
        );
    }

    @Test
    void updateCreditLimitFailureInvalidValue() {
        Costumer costumer = new Costumer();
        costumer.setId(1L);
        costumer.setCreditLimit(BigDecimal.valueOf(1000));

        when(costumerRepository.findById(1L)).thenReturn(Optional.of(costumer));

        assertThrows(IllegalArgumentException.class, () ->
                creditLimitService.updateCreditLimit(1L, BigDecimal.valueOf(-100), "admin")
        );
    }

    @Test
    void updateCreditLimitFailureVipMinValue() {
        Costumer costumer = new Costumer();
        costumer.setId(1L);
        costumer.setCreditLimit(BigDecimal.valueOf(10000));
        costumer.setVip(true);

        when(creditLimitConfig.getVipMinCreditValue()).thenReturn(BigDecimal.valueOf(5000));
        when(costumerRepository.findById(1L)).thenReturn(Optional.of(costumer));

        assertThrows(IllegalArgumentException.class, () ->
                creditLimitService.updateCreditLimit(1L, BigDecimal.valueOf(4000), "admin")
        );
    }

    @Test
    void updateCreditLimitFailureNullCreditLimit() {
        Costumer costumer = new Costumer();
        costumer.setId(1L);
        costumer.setCreditLimit(BigDecimal.valueOf(1000));

        when(costumerRepository.findById(1L)).thenReturn(Optional.of(costumer));

        assertThrows(IllegalArgumentException.class, () ->
                creditLimitService.updateCreditLimit(1L, null, "admin")
        );
    }

    @Test
    void shouldSaveHistoryOnLimitUpdate() {
        Costumer costumer = new Costumer();
        costumer.setId(1L);
        costumer.setCreditLimit(BigDecimal.valueOf(1000));
        costumer.setVip(false);

        when(costumerRepository.findById(1L)).thenReturn(Optional.of(costumer));
        creditLimitService.updateCreditLimit(1L, BigDecimal.valueOf(2000), "admin");

        verify(creditLimitHistoryRepository, times(1)).save(argThat(history ->
                history.getCustomerId().equals(1L) &&
                        history.getOldLimit().equals(BigDecimal.valueOf(1000)) &&
                        history.getNewLimit().equals(BigDecimal.valueOf(2000)) &&
                        history.getChangedBy().equals("admin") &&
                        history.getChangedAt() != null
        ));
    }

}
