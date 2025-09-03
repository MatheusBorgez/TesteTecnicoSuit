package suit.testepraticosuit.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

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
