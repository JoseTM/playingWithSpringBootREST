package org.pecera.demorest.restcontroller;

import org.pecera.demorest.model.Country;
import org.pecera.demorest.restDto.CountryDto;
import org.pecera.demorest.restDto.factory.CountryDtoFactory;
import org.pecera.demorest.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    CountryService countryService;


    @GetMapping("/countries")
    public List<CountryDto> countries (){
        List<Country> countries = countryService.findAll();
        return CountryDtoFactory.newDtosFrom(countries);
    }

    @GetMapping(value = "/countriesByFirstLetter")
    public List<CountryDto> countriesByFirstLetter (@RequestParam(value = "firstLetter") char firstLetter){

        return getCountryDtos(firstLetter);
    }

    @RequestMapping(value = "/countryNameFirstLetterIs/{firstLetter}", method = RequestMethod.GET)
    public List<CountryDto> countryNameFirstLetterIs (@PathVariable(value = "firstLetter") char firstLetter){

        return getCountryDtos(firstLetter);
    }

    private List<CountryDto> getCountryDtos(@PathVariable(value = "firstLetter") char firstLetter) {
        if (String.valueOf(firstLetter).isBlank() || String.valueOf(firstLetter).isEmpty()) {
            return CountryDtoFactory.newDtosEmpty();
        }

        List<Country> countries = countryService.findByNameFirstLetter(firstLetter);
        return CountryDtoFactory.newDtosFrom(countries);
    }

}
