package org.pecera.demorest.model;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "country_country_id_seq")
    private Long countryId;
    private String country;

    public Country() {
    }

    public Country( String country) {
        this.country = country;
    }

    @Column(name = "country_id")
    public Long getCountryId() {
        return countryId;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

}
