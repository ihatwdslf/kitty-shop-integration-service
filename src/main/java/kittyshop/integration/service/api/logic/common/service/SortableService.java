package kittyshop.integration.service.api.logic.common.service;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface SortableService {

    List<Sort.Order> getSortOrder();

    List<Sort.Order> ID_SORT_ORDER = List.of(new Sort.Order(Sort.Direction.ASC, "id"));
}
