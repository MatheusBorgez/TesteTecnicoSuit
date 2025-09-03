package suit.testepraticosuit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import suit.testepraticosuit.domain.CreditLimitHistory;

@Repository
public interface CostumerLimitHistoryRepository extends JpaRepository<CreditLimitHistory, Long> {

}
