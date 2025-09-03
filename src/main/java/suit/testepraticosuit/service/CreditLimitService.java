package suit.testepraticosuit.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    @Transactional
    public void updateCreditLimit(Long costumerId, BigDecimal newLimit, String updatedBy) {
        // L�gica para atualizar o limite de cr�dito do cliente
        // 1. Buscar o cliente pelo ID
        // 2. Atualizar o limite de cr�dito
        // 3. Salvar o hist�rico da altera��o no CostumerLimitHistory
        // 4. Salvar as altera��es no cliente
    }

    public BigDecimal getCreditLimit(Long costumerId) {
        // L�gica para obter o limite de cr�dito do cliente
        return BigDecimal.ZERO;
    }

    public List<CreditLimitHistory> getCreditLimitHistory(Long costumerId) {
        // L�gica para obter o hist�rico de altera��es do limite de cr�dito do cliente
        return new ArrayList<>();
    }


}
