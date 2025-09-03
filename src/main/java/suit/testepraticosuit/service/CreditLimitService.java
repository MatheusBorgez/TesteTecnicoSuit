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
        // Lógica para atualizar o limite de crédito do cliente
        // 1. Buscar o cliente pelo ID
        // 2. Atualizar o limite de crédito
        // 3. Salvar o histórico da alteração no CostumerLimitHistory
        // 4. Salvar as alterações no cliente
    }

    public BigDecimal getCreditLimit(Long costumerId) {
        // Lógica para obter o limite de crédito do cliente
        return BigDecimal.ZERO;
    }

    public List<CreditLimitHistory> getCreditLimitHistory(Long costumerId) {
        // Lógica para obter o histórico de alterações do limite de crédito do cliente
        return new ArrayList<>();
    }


}
