package org.pecera.demorest.services.implementations;

import org.pecera.demorest.model.Country;
import org.pecera.demorest.repository.CountryRepository;
import org.pecera.demorest.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;


    @Override
    public List<Country> findAll(){
        List<Country> countries = new ArrayList<>();
        countryRepository.findAll().forEach(
                countries::add
                );

        return countries;
    }

    @Override
    public List<Country> findByNameFirstLetter(char firstLetter){
       return countryRepository.findContriesWithNameStartingWithLetter(firstLetter);
    }

}
