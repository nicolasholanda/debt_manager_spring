package com.github.nicolasholanda.debt.model.dto;

import com.github.nicolasholanda.debt.model.Product;

import java.math.BigDecimal;

public class ProductListItemDTO extends BaseDTO<Integer> {

    private String name;
    private String brand;
    private BigDecimal price;

    public ProductListItemDTO(Product product) {
        super(product.getId());
        this.name = product.getName();
        this.price = product.getPrice();
        this.brand = product.getBrand().getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
