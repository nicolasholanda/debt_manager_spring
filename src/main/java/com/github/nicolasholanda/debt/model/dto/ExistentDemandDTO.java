package com.github.nicolasholanda.debt.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.nicolasholanda.debt.model.Address;
import com.github.nicolasholanda.debt.model.Demand;
import com.github.nicolasholanda.debt.model.Payment;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ExistentDemandDTO extends BaseDTO<Integer> {
    private String storeName;
    private Integer customerId;
    private Set<DemandItemDTO> items;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date shippedDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date requestDate;

    private Address shipAddress;

    private List<Payment> payments;

    private Integer status;

    public ExistentDemandDTO(Demand demand) {
        super(demand.getId());
        this.storeName = demand.getStore().getName();
        this.customerId = demand.getCustomer().getId();
        this.shippedDate = demand.getShippedDate();
        this.requestDate = demand.getRequestDate();
        this.shipAddress = demand.getShipAddress();
        this.payments = demand.getPayments();
        this.status = demand.getStatus().getCode();
        this.items = demand.getItems().stream().map(DemandItemDTO::new).collect(Collectors.toSet());
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

    public Set<DemandItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<DemandItemDTO> items) {
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

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
