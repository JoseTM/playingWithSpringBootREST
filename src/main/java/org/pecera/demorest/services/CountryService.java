package org.pecera.demorest.services;

import org.pecera.demorest.model.Country;

import java.util.List;

public interface CountryService {
    public List<Country> findAll();
}
