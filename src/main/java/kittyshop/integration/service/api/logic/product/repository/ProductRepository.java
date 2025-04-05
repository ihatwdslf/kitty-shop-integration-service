package kittyshop.integration.service.api.logic.product.repository;

import kittyshop.integration.service.api.logic.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    @EntityGraph(attributePaths = {"categories", "brand"})
    Optional<Product> findById(Long id);

    @EntityGraph(attributePaths = {"categories", "brand"})
    Page<Product> findAll(Specification<Product> spec, Pageable pageable);

    @EntityGraph(attributePaths = {"categories", "brand"})
    List<Product> findAllByIdIn(Set<Long> ids);
}
