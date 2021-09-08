package org.pecera.demorest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pecera.demorest.model.Country;
import org.pecera.demorest.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CountryRestServiceTest {

    @Autowired
    CountryService countryService;

    @Test
    public void country_rest_service_retrieve_something(){
        List<Country> countries = countryService.findAll();

        Assertions.assertThat(countries.size() > 0);
    }
}
