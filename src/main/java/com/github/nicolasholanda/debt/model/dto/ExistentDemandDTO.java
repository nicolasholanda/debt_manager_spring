package com.github.nicolasholanda.debt.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.nicolasholanda.debt.model.Address;
import com.github.nicolasholanda.debt.model.Demand;
import com.github.nicolasholanda.debt.model.Payment;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class ExistentDemandDTO extends BaseDTO<Integer> {
    private String storeName;
    private Integer customerId;
    private Set<ExistentDemandItemDTO> items;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date shippedDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date requestDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date shipDate;

    private Address shipAddress;

    private Payment payment;

    private Integer status;

    public ExistentDemandDTO(Demand demand) {
        super(demand.getId());
        this.storeName = demand.getStore().getName();
        this.customerId = demand.getCustomer().getId();
        this.shippedDate = demand.getShippedDate();
        this.requestDate = demand.getRequestDate();
        this.shipAddress = demand.getShipAddress();
        this.payment = demand.getPayment();
        this.status = demand.getStatus().getCode();
        this.items = demand.getItems().stream().map(ExistentDemandItemDTO::new).collect(Collectors.toSet());
        this.shipDate = demand.getShipDate();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Set<ExistentDemandItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<ExistentDemandItemDTO> items) {
        this.items = items;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Address getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(Address shipAddress) {
        this.shipAddress = shipAddress;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }
}
