package suit.testepraticosuit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import suit.testepraticosuit.domain.Costumer;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Long> {
}
