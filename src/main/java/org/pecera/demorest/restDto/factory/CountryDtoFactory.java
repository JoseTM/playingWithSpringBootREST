package org.pecera.demorest.restDto.factory;

import org.pecera.demorest.model.Country;
import org.pecera.demorest.restDto.CountryDto;

import java.util.ArrayList;
import java.util.List;

public class CountryDtoFactory {


    public static CountryDto newDtoFrom(Country country){
        return  CountryDto.newDto(country.getCountryId(), country.getCountry());
    }

    public static List<CountryDto> newDtosFrom(List<Country> countries){
        List <CountryDto> countriesDtos = new ArrayList<>();
        countries.forEach((
               country -> countriesDtos.add(
                                CountryDto.newDto(country.getCountryId(),country.getCountry()
                                ))
               )) ;

      return countriesDtos;
    }

    public static List<CountryDto> newDtosEmpty(){
        return new ArrayList<>();
    }

}
