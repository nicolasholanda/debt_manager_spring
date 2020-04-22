package com.github.nicolasholanda.debt.model.dto;

import com.github.nicolasholanda.debt.model.DemandItem;

import java.math.BigDecimal;

public class DemandItemDTO {
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal discount;
    private String productName;

    public DemandItemDTO(DemandItem item) {
        this.quantity = item.getQuantity();
        this.discount = item.getDiscount();
        this.productName = item.getProduct().getName();
        this.price = item.getProduct().getPrice().multiply(BigDecimal.valueOf(quantity)).subtract(discount);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
