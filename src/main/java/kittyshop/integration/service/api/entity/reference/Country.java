package kittyshop.integration.service.api.entity.reference;

import jakarta.persistence.*;
import kittyshop.integration.service.api.entity.details.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
public class Country {
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true, length = 2)
    private String code;

    @OneToMany(mappedBy = "country")
    private Set<Brand> brands = new HashSet<>();
}
