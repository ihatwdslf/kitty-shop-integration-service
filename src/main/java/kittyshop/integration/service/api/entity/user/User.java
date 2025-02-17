package kittyshop.integration.service.api.entity.user;

import jakarta.persistence.*;
import kittyshop.integration.service.api.entity.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String firstName;
    private String lastName;
    private Long phone;
    private String address;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new HashSet<>();
}
