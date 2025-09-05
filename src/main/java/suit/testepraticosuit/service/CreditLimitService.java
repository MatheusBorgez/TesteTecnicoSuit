package suit.testepraticosuit.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import suit.testepraticosuit.config.CreditLimitConfig;
import suit.testepraticosuit.domain.Costumer;
import suit.testepraticosuit.domain.CreditLimitHistory;
import suit.testepraticosuit.repository.CreditLimitHistoryRepository;
import suit.testepraticosuit.repository.CostumerRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditLimitService {

    private final CreditLimitHistoryRepository creditLimitHistoryRepository;
    private final CostumerRepository costumerRepository;
    private final CreditLimitConfig creditLimitConfig;

    @Transactional
    public void updateCreditLimit(Long costumerId, BigDecimal newLimit, String updatedBy) {

        if (newLimit == null || newLimit.signum() == -1) {
            throw new IllegalArgumentException("The new credit limit cannot be a negative number.");
        }

        var costumer = costumerRepository.findById(costumerId).orElseThrow(() -> new IllegalArgumentException("Costumer not found"));

        if (costumer.isVip() && newLimit.compareTo(creditLimitConfig.getVipMinCreditValue()) < 0) {
            throw new IllegalArgumentException("VIP Costumers cannot have less credit limit than " + creditLimitConfig.getVipMinCreditValue());
        }

        BigDecimal oldLimit = costumer.getCreditLimit();
        costumer.setCreditLimit(newLimit);
        saveHistory(newLimit, updatedBy, costumer, oldLimit);
        costumerRepository.save(costumer);
    }

    public BigDecimal getCreditLimit(Long costumerId) {
        return costumerRepository.findById(costumerId)
                .orElseThrow(() -> new IllegalArgumentException("Costumer not found"))
                .getCreditLimit();
    }

    public Page<CreditLimitHistory> getCreditLimitHistory(Long costumerId, Pageable pageable) {

        if (!costumerRepository.existsById(costumerId)) {
            throw new IllegalArgumentException("Costumer not found");
        }

        if (pageable.getSort().isUnsorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by(Sort.Direction.DESC, "changedAt"));
        }

        return creditLimitHistoryRepository.findByCustomerId(costumerId, pageable);
    }

    private void saveHistory(BigDecimal newLimit, String updatedBy, Costumer costumer, BigDecimal oldLimit) {
        var history = new CreditLimitHistory();
        history.setCustomerId(costumer.getId());
        history.setOldLimit(oldLimit);
        history.setNewLimit(newLimit);
        history.setChangedBy(updatedBy);
        history.setChangedAt(java.time.LocalDateTime.now());

        creditLimitHistoryRepository.save(history);
    }

}
