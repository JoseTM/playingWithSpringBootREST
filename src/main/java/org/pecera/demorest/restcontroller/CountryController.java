package org.pecera.demorest.restcontroller;

import org.pecera.demorest.model.Country;
import org.pecera.demorest.repository.CountryRepository;
import org.pecera.demorest.restDto.CountryDto;
import org.pecera.demorest.restDto.factory.CountryDtoFactory;
import org.pecera.demorest.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CountryController {
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    CountryService countryService;


    @GetMapping("/countries")
    public List<CountryDto> countries (){
        List<Country> countries = countryService.findAll();

        return CountryDtoFactory.newDtosFrom(countries);
    }

}
