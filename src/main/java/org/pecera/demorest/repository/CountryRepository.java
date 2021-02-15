package org.pecera.demorest.repository;

import org.pecera.demorest.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long>, CountryRepositoryCustom {
}
