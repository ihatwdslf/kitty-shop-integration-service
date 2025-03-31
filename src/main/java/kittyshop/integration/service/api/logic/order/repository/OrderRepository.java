package kittyshop.integration.service.api.logic.order.repository;

import kittyshop.integration.service.api.logic.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"user", "status", "paymentMethod", "orderItems"})
    Page<Order> findAllByUserEmail(String userEmail, Pageable pageable);

    @EntityGraph(attributePaths = {"user", "status", "paymentMethod", "orderItems"})
    Optional<Order> findById(Long id);
}
