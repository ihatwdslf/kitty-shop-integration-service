package kittyshop.integration.service.api.logic.reference.repository;

import kittyshop.integration.service.api.logic.reference.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    Optional<PaymentMethod> findByKey(String key);
}
