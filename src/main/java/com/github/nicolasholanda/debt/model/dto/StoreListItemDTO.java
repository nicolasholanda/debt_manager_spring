package com.github.nicolasholanda.debt.model.dto;

import com.github.nicolasholanda.debt.model.Brand;
import com.github.nicolasholanda.debt.model.Store;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class StoreListItemDTO extends BaseDTO<Integer> implements Comparable<StoreListItemDTO> {
    private String name;
    private BigDecimal distance;
    private BigDecimal ratingAverage;
    private List<String> brands;

    public StoreListItemDTO(String name, BigDecimal distance, BigDecimal ratingAverage, List<String> brands) {
        this.name = name;
        this.distance = distance;
        this.ratingAverage = ratingAverage;
        this.brands = brands;
    }

    public StoreListItemDTO(Store store) {
        super.setId(store.getId());
        this.name = store.getName();
        this.ratingAverage = store.getRatingAverage();
        this.brands = store.getBrands().stream().map(Brand::getName).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public BigDecimal getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(BigDecimal ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    @Override
    public int compareTo(StoreListItemDTO s) {
        if(getDistance() == null || s.getDistance() == null) {
            return 0;
        }
        return getDistance().compareTo(s.getDistance());
    }
}
