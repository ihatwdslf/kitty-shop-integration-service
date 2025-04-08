package kittyshop.integration.service.api.logic.reference.repository;

import kittyshop.integration.service.api.logic.reference.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {

    Optional<Status> findByKey(String key);
}
