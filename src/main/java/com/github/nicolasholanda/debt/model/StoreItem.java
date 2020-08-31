package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "store_item")
public class StoreItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @JsonIgnore
    private StoreItemPK id = new StoreItemPK();

    @Min(value = 0, message = "{storeitem.quantity.min}")
    @NotNull(message = "{storeitem.quantity.notnull}")
    private Integer quantity;

    public StoreItem() {
    }

    public StoreItem(Product product, Store store, Integer quantity) {
        this.id.setStore(store);
        this.id.setProduct(product);
        this.quantity = quantity;
    }

    public StoreItemPK getId() {
        return id;
    }

    public Product getProduct() {
        return id.getProduct();
    }

    @JsonIgnore
    public Store getStore() {
        return id.getStore();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
