package org.pecera.demorest.repository.implementations;

import org.pecera.demorest.model.Country;
import org.pecera.demorest.repository.CountryRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CountryRepositoryCustomImpl implements CountryRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Country> findContriesWithNameStartingWithLetter(char firstLetter) {
        String queryString = "from Country as con" +
                " where upper(con.country) like :char";

        Query query = entityManager.createQuery(queryString, Country.class);
        query.setParameter("char", String.valueOf(firstLetter).toUpperCase()+"%%");

        return query.getResultList();
    }
}
