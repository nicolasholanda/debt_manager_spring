package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "address")
public class Address extends BaseEntity<Integer> {

    @NotNull(message = "{address.city.notnull}")
    @Size(min = 2, max = 150, message = "{address.city.size}")
    private String city;

    @NotNull(message = "{address.state.notnull}")
    @Size(min = 2, max = 150, message = "{address.state.size}")
    private String state;

    @NotNull(message = "{address.street.notnull}")
    @Size(min = 1, max = 200, message = "{address.street.size}")
    private String street;

    @NotNull(message = "{address.number.notnull}")
    @Size(max = 100, message = "{address.number.size}")
    private String number;

    @NotNull(message = "{address.zipcode.notnull}")
    @Size(min = 8, max = 8, message = "{address.zipcode.size}")
    private String zipCode;

    @NotNull(message = "{address.country.notnull}")
    @Size(min = 1, max = 200, message = "{address.country.size}")
    private String country;

    @NotNull(message = "{address.district.notnull}")
    @Size(min = 1, max = 200, message = "{address.district.size}")
    private String district;

    @NotNull(message = "{address.latitude.notnull}")
    @Digits(integer = 2, fraction = 14, message = "{address.longitude.value}")
    private BigDecimal latitude;

    @NotNull(message = "{address.longitude.notnull}")
    @Digits(integer = 3, fraction = 14, message = "{address.longitude.value}")
    private BigDecimal longitude;

    @Size(min = 0, max = 8, message = "{address.complement.size}")
    private String complement;

    @Size(min = 0, max = 40, message = "{address.referencepoint.size}")
    private String referencePoint;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private ApplicationUser user;

    public Address() {
    }

    public Address(String zipCode, String city, String state, String street, String number, String country, String district, BigDecimal latitude, BigDecimal longitude, String complement, String referencePoint, ApplicationUser user) {
        this.city = city;
        this.user = user;
        this.state = state;
        this.street = street;
        this.number = number;
        this.country = country;
        this.zipCode = zipCode;
        this.district = district;
        this.latitude = latitude;
        this.longitude = longitude;
        this.complement = complement;
        this.referencePoint = referencePoint;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getReferencePoint() {
        return referencePoint;
    }

    public void setReferencePoint(String referencePoint) {
        this.referencePoint = referencePoint;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
