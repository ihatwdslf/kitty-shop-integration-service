package kittyshop.integration.service.api.logic.reference.service;

import kittyshop.integration.service.api.logic.common.dto.ListResponseDto;
import kittyshop.integration.service.api.logic.reference.dto.CountryResponseDto;
import kittyshop.integration.service.api.logic.reference.entity.Country;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CountryService {

    ListResponseDto<CountryResponseDto> findAll(Pageable pageable);

    Optional<Country> findById(Long id);

    Optional<Country> findByName(String name);

    Optional<Country> findByCode(String code);

    void deleteById(Long id);
}
