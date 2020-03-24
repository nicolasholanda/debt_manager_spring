package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "demand_item")
public class DemandItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @JsonIgnore
    private DemandItemPK id = new DemandItemPK();

    @Min(value = 1, message = "{demanditem.quantity.min}")
    @NotNull(message = "{demanditem.quantity.notnull}")
    private Integer quantity;

    @Min(value = 0, message = "{demanditem.discount.min}")
    @NotNull(message = "{demanditem.discount.notnull}")
    private BigDecimal discount;

    private BigDecimal price;

    public DemandItem() {
    }

    public DemandItem(Product product, Demand demand, Integer quantity, BigDecimal discount, BigDecimal price) {
        id.setDemand(demand);
        id.setProduct(product);
        this.quantity = quantity;
        this.discount = discount;
        this.price = price;
    }

    public DemandItemPK getId() {
        return id;
    }

    public void setId(DemandItemPK id) {
        this.id = id;
    }

    public Product getProduct() {
        return id.getProduct();
    }

    @JsonIgnore
    public Demand getDemand() {
        return id.getDemand();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
