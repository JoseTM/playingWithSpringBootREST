package org.pecera.demorest.repository;

import org.pecera.demorest.model.Country;

import java.util.List;

public interface CountryRepositoryCustom {

    List<Country> findContriesWithNameStartingWithLetter(char firstLetter);
}
