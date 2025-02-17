package kittyshop.integration.service.api.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class UserRole {
    @Id
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String role;

    @OneToMany(mappedBy = "role")
    private final Set<User> users = new HashSet<>();
}
