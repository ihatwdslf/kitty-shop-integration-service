package kittyshop.integration.service.api.logic.user.repository;

import kittyshop.integration.service.api.logic.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByRole(String role);
}
