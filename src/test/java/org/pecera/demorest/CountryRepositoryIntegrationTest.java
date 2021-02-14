package org.pecera.demorest;

import org.junit.jupiter.api.Test;
import org.pecera.demorest.model.Country;
import org.pecera.demorest.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CountryRepositoryIntegrationTest {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void whenCalledSave_thenCorrectNumberOfCountries(){
        countryRepository.save(new Country("Tenerife"));
        List<Country> countries = (List<Country>) countryRepository.findAll();
    }

}
