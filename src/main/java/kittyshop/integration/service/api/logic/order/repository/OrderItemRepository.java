package kittyshop.integration.service.api.logic.order.repository;

import kittyshop.integration.service.api.logic.order.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findByOrderIdAndProductId(Long orderId, Long productId);

    Page<OrderItem> findAllByOrderId(Long id, Pageable pageable);
    
    void deleteByOrderIdAndProductId(Long orderId, Long productId);
}
