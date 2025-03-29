package kittyshop.integration.service.api.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class UserRole implements GrantedAuthority {
    @Id
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String role;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<User> users = new HashSet<>();

    public String getAuthority() {
        return role;
    }
}
