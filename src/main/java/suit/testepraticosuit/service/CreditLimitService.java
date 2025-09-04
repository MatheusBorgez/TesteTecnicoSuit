package suit.testepraticosuit.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import suit.testepraticosuit.config.CreditLimitConfig;
import suit.testepraticosuit.domain.Costumer;
import suit.testepraticosuit.domain.CreditLimitHistory;
import suit.testepraticosuit.repository.CostumerLimitHistoryRepository;
import suit.testepraticosuit.repository.CostumerRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditLimitService {

    private final CostumerLimitHistoryRepository costumerLimitHistoryRepository;
    private final CostumerRepository costumerRepository;
    private final CreditLimitConfig creditLimitConfig;

    @Transactional
    public void updateCreditLimit(Long costumerId, BigDecimal newLimit, String updatedBy) {

        if (newLimit == null || newLimit.signum() == -1) {
            throw new IllegalArgumentException("The new credit limit cannot be a negative number.");
        }

        var costumer = costumerRepository.findById(costumerId).orElseThrow(() -> new IllegalArgumentException("Costumer not found"));

        if (costumer.isVip() && newLimit.compareTo(creditLimitConfig.getVipMinCreditValue()) < 0) {
            throw new IllegalArgumentException("VIP Costumers cannot have less credit limit then " + creditLimitConfig.getVipMinCreditValue());
        }

        BigDecimal oldLimit = costumer.getCreditLimit();
        costumer.setCreditLimit(newLimit);
        saveHistory(newLimit, updatedBy, costumer, oldLimit);
        costumerRepository.save(costumer);
    }

    public BigDecimal getCreditLimit(Long costumerId) {
        // L�gica para obter o limite de cr�dito do cliente
        return BigDecimal.ZERO;
    }

    public List<CreditLimitHistory> getCreditLimitHistory(Long costumerId) {
        // L�gica para obter o hist�rico de altera��es do limite de cr�dito do cliente
        return new ArrayList<>();
    }

    private void saveHistory(BigDecimal newLimit, String updatedBy, Costumer costumer, BigDecimal oldLimit) {
        var history = new CreditLimitHistory();
        history.setCustomerId(costumer.getId());
        history.setOldLimit(oldLimit);
        history.setNewLimit(newLimit);
        history.setChangedBy(updatedBy);
        history.setChangedAt(java.time.LocalDateTime.now());

        costumerLimitHistoryRepository.save(history);
    }

}
