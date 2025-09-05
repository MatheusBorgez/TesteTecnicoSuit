package suit.testepraticosuit.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import suit.testepraticosuit.domain.CreditLimitHistory;
import suit.testepraticosuit.dto.UpdateLimitRequest;
import suit.testepraticosuit.service.CreditLimitService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/credit-limits")
@RequiredArgsConstructor
public class CreditLimitController {

    private final CreditLimitService creditLimitService;

    @GetMapping("/{customerId}")
    public ResponseEntity<BigDecimal> getCreditLimit(@PathVariable Long customerId) {
        return ResponseEntity.ok(creditLimitService.getCreditLimit(customerId));
    }

    @GetMapping("/{customerId}/history")
    public ResponseEntity<Page<CreditLimitHistory>> getCreditLimitHistory(@PathVariable Long customerId, Pageable pageable) {
        return ResponseEntity.ok(creditLimitService.getCreditLimitHistory(customerId, pageable));
    }

    @PutMapping("/{customerId}")
    @PreAuthorize("hasRole('CREDIT_LIMIT_ADMIN')")
    public ResponseEntity<Void> updateCreditLimit(@PathVariable Long customerId, @RequestBody @Valid UpdateLimitRequest request, Authentication auth) {
        creditLimitService.updateCreditLimit(customerId, request.newLimit(), auth.getName());
        return ResponseEntity.ok().build();
    }

}
