package com.github.nicolasholanda.debt.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class NewDemandItemDTO {

    @Min(value = 1, message = "{demanditem.quantity.min}")
    @NotNull(message = "{demanditem.quantity.notnull}")
    private Integer quantity;

    @NotNull(message = "{demanditem.product.notnull}")
    private Integer productId;

    @Min(value = 0, message = "{demanditem.discount.min}")
    @NotNull(message = "{demanditem.discount.notnull}")
    private BigDecimal discount;

    public NewDemandItemDTO() {}

    public NewDemandItemDTO(Integer quantity, Integer productId, BigDecimal discount) {
        this.quantity = quantity;
        this.productId = productId;
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
