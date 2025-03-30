package kittyshop.integration.service.api.logic.common.service;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface SortableService {

    List<Sort.Order> getSortOrder();
}
