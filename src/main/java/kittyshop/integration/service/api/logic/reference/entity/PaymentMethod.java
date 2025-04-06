package kittyshop.integration.service.api.logic.reference.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kittyshop.integration.service.api.logic.order.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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

    @Column(nullable = false)
    private String key;

    @JsonIgnore
    @OneToMany(mappedBy = "paymentMethod")
    private Set<Order> orders = new HashSet<>();

    @Getter
    @RequiredArgsConstructor
    public enum Defaults {
        ON_DELIVERY("on_delivery_pay"),
        CREDIT_CARD("online_pay_credit_card"),
        PRIVAT24("online_pay_privat24"),
        APPLE("online_pay_apple"),
        GOOGLE("online_pay_google"),
        CREDIT_FUNDS("credit_funds_pay"),
        COMPANY("company_pay");
        
        private final String key;
    }
}
