package kittyshop.integration.service.api.entity.reference;

import jakarta.persistence.*;
import kittyshop.integration.service.api.entity.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payment_methods")
@Getter
@Setter
@NoArgsConstructor
public class PaymentMethod {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "paymentMethod")
    private Set<Order> orders = new HashSet<>();
}
