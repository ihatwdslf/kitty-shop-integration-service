package kittyshop.integration.service.api.entity.details;

import jakarta.persistence.*;
import kittyshop.integration.service.api.entity.reference.Country;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
public class Brand {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private String website;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "brand")
    private Set<Product> products = new HashSet<>();
}
