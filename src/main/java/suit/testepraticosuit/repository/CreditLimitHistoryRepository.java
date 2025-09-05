package suit.testepraticosuit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import suit.testepraticosuit.domain.CreditLimitHistory;

import java.util.List;

@Repository
public interface CreditLimitHistoryRepository extends JpaRepository<CreditLimitHistory, Long> {

    Page<CreditLimitHistory> findByCustomerId(Long customerId, Pageable pageable);

}
