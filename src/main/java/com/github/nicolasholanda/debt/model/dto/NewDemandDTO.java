package com.github.nicolasholanda.debt.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.nicolasholanda.debt.model.Payment;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

public class NewDemandDTO {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @NotNull(message = "{demand.requestdate.notnull}")
    private Date requestDate;

    @NotNull(message = "{demand.customer.notnull}")
    private Integer customerId;

    @NotNull(message = "{demand.shipaddress.notnull}")
    private Integer addressId;

    @NotNull(message = "{demand.store.notnull}")
    private Integer storeId;

    @NotNull(message = "{demand.items.notnull}")
    private Set<NewDemandItemDTO> items;

    @NotNull(message = "{demand.payments.notnull}")
    private Payment payment;

    public NewDemandDTO() {}

    public NewDemandDTO(Date requestDate, Integer customerId, Integer addressId, Integer storeId, Set<NewDemandItemDTO> items, Payment payment) {
        this.requestDate = requestDate;
        this.customerId = customerId;
        this.addressId = addressId;
        this.storeId = storeId;
        this.items = items;
        this.payment = payment;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Set<NewDemandItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<NewDemandItemDTO> items) {
        this.items = items;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
