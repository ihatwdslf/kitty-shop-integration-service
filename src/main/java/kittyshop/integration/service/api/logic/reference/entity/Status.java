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
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "status")
    private Set<Order> orders = new HashSet<>();

    @Getter
    @RequiredArgsConstructor
    public enum Defaults {
        PENDING("PENDING", "Pending"),
        PROCESSING("PROCESSING", "Processing"),
        SHIPPED("SHIPPED", "Shipped"),
        DELIVERED("DELIVERED", "Delivered"),
        CANCELLED("CANCELLED", "Cancelled");

        private final String key;
        private final String name;
    }
}
