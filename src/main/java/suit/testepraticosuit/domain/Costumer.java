package suit.testepraticosuit.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "vip")
    private boolean isVip;

    @Column(nullable = false)
    private BigDecimal creditLimit;

}
