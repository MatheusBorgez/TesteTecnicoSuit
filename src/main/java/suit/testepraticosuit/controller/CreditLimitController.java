package suit.testepraticosuit.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import suit.testepraticosuit.domain.CreditLimitHistory;
import suit.testepraticosuit.dto.UpdateLimitRequest;
import suit.testepraticosuit.service.CreditLimitService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/credit-limits")
@RequiredArgsConstructor
public class CreditLimitController {

    private final CreditLimitService creditLimitService;

    @GetMapping("/{id}")
    public ResponseEntity<BigDecimal> getCreditLimit(@PathVariable Long customerId) {
        // L�gica para obter o limite de cr�dito do cliente
        return ResponseEntity.ok(creditLimitService.getCreditLimit(customerId));
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<CreditLimitHistory>> getCreditLimitHistory(Long customerId) {
        // L�gica para obter o hist�rico de altera��es do limite de cr�dito do cliente
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CREDIT_LIMIT_ADMIN')")
    public ResponseEntity<Void> updateCreditLimit(@PathVariable Long costumerId, @RequestBody @Valid UpdateLimitRequest request, Authentication auth) {
        // L�gica para atualizar o limite de cr�dito do cliente
        creditLimitService.updateCreditLimit(request.costumerId(), request.newLimit(), auth.getName());
        return ResponseEntity.ok().build();
    }

}
