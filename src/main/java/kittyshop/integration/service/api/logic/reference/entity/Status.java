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
@Table(name = "statuses")
@Getter
@Setter
@NoArgsConstructor
public class Status {
    @Id
    private Long id;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "status")
    private Set<Order> orders = new HashSet<>();

    @Getter
    @RequiredArgsConstructor
    public enum Defaults {
        PENDING("pending", "Очікується"),
        PROCESSING("processing", "Обробляється"),
        SHIPPED("shipper", "Відправлено"),
        DELIVERED("delivered", "Доставлено"),
        CANCELLED("cancelled", "Скасовано");

        private final String key;
        private final String name;
    }
}
