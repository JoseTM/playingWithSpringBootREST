package org.pecera.demorest.restDto;

public class CountryDto {
    private final Long cuntryId;
    private final String countryName;

    private CountryDto(Long cuntryId, String countryName) {
        this.cuntryId = cuntryId;
        this.countryName = countryName;
    }

    public static CountryDto newDto(Long cuntryId, String country) {
        return new CountryDto(cuntryId, country);
    }

    public Long getCuntryId() {
        return cuntryId;
    }

    public String getCountryName() {
        return countryName;
    }
}
