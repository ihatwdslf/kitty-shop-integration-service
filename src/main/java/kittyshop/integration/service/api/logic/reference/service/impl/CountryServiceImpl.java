package kittyshop.integration.service.api.logic.reference.service.impl;

import kittyshop.integration.service.api.logic.common.dto.ListResponseDto;
import kittyshop.integration.service.api.logic.reference.dto.CountryResponseDto;
import kittyshop.integration.service.api.logic.reference.entity.Country;
import kittyshop.integration.service.api.logic.reference.mapper.CountryMapper;
import kittyshop.integration.service.api.logic.reference.repository.CountryRepository;
import kittyshop.integration.service.api.logic.reference.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryMapper countryMapper;
    private final CountryRepository countryRepository;

    @Override
    public ListResponseDto<CountryResponseDto> findAll(Pageable pageable) {
        Page<Country> countriesPage = countryRepository.findAll(pageable);
        return new ListResponseDto<CountryResponseDto>()
                .setList(countriesPage.stream()
                        .map(countryMapper::toCountryResponseDto)
                        .toList())
                .setTotalRows(countriesPage.getTotalElements());
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> findByName(String name) {
        return countryRepository.findByName(name);
    }

    @Override
    public Optional<Country> findByCode(String code) {
        return countryRepository.findByCode(code);
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
