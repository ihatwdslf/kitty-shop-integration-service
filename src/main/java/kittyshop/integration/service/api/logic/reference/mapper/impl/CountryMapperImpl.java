package kittyshop.integration.service.api.logic.reference.mapper.impl;

import kittyshop.integration.service.api.logic.reference.dto.CountryResponseDto;
import kittyshop.integration.service.api.logic.reference.entity.Country;
import kittyshop.integration.service.api.logic.reference.mapper.CountryMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class CountryMapperImpl implements CountryMapper {

    @Override
    public CountryResponseDto toCountryResponseDto(Country country) {
        if (country == null) {
            return null;
        }

        CountryResponseDto countryResponseDto = new CountryResponseDto();

        countryResponseDto.setId(country.getId());
        countryResponseDto.setName(country.getName());
        countryResponseDto.setCode(country.getCode());

        log.info("Mapped country response: {}", countryResponseDto);
        return countryResponseDto;
    }
}
