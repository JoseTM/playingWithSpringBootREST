package org.pecera.demorest.repository.implementations;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


/**
 * Uso de este repositorio base custom:
 *
 *  @Configuration
 *  @EnableJpaRepositories(repositoryBaseClass = MyRepositoryImpl.class)
 *  class ApplicationConfiguration { … }
 *
 */


public class MyRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> {

    private EntityManager entityManager;

    public MyRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }



    @Transactional
    public <S extends T> S save(S entity) {
        return super.save(entity);
    }
}
