package com.github.nicolasholanda.debt.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptySet;

@Entity
@Table(name = "demand")
public class Demand extends BaseEntity<Integer> {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date shipDate;

    @NotNull(message = "{demand.requestdate.notnull}")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date requestDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date shippedDate;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @NotNull(message = "{demand.shipaddress.notnull}")
    private Address shipAddress;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull(message = "{demand.customer.notnull}")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "store_id")
    @NotNull(message = "{demand.store.notnull}")
    private Store store;

    @OneToMany(mappedBy = "demand")
    @NotNull(message = "{demand.payments.notnull}")
    private List<Payment> payments;

    @OneToMany(mappedBy = "id.demand")
    @NotNull(message = "{demand.items.notnull}")
    private Set<DemandItem> items = emptySet();

    public Demand(){}

    public Demand(Date shipDate, Date requestDate, Date shippedDate, Address shipAddress, Customer customer, Store store) {
        this.shipDate = shipDate;
        this.requestDate = requestDate;
        this.shippedDate = shippedDate;
        this.shipAddress = shipAddress;
        this.customer = customer;
        this.store = store;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Address getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(Address shipAddress) {
        this.shipAddress = shipAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Set<DemandItem> getItems() {
        return items;
    }

    public void setItems(Set<DemandItem> items) {
        this.items = items;
    }
}
