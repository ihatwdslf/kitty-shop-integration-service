package kittyshop.integration.service.api.logic.category.repository;

import kittyshop.integration.service.api.logic.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

    List<Category> findByParentId(Long parentId);
}
