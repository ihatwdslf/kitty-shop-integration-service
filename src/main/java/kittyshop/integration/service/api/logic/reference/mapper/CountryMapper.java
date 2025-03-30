package kittyshop.integration.service.api.logic.reference.mapper;

import kittyshop.integration.service.api.logic.reference.dto.CountryResponseDto;
import kittyshop.integration.service.api.logic.reference.entity.Country;

public interface CountryMapper {

    CountryResponseDto toCountryResponseDto(Country country);
}
