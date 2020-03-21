package com.github.nicolasholanda.debt.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address extends BaseEntity<Integer> {

    private String city;
    private String street;
    private String number;
    private String zipCode;
    private String country;
    private String district;
    private Double latitude;
    private Double longitude;
    private String complement;
    private String referencePoint;

    public Address() {
    }

    public Address(String zipCode, String city, String street, String number, String country, String district, Double latitude, Double longitude, String complement) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.country = country;
        this.zipCode = zipCode;
        this.district = district;
        this.latitude = latitude;
        this.longitude = longitude;
        this.complement = complement;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
