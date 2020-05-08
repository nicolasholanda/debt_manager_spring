package com.github.nicolasholanda.debt.model;

import com.github.nicolasholanda.debt.model.enuns.DemandStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static java.util.Collections.emptySet;

@Entity
@Table(name = "demand")
public class Demand extends BaseEntity<Integer> {

    private Date shipDate;

    private Date requestDate;

    private Date shippedDate;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address shipAddress;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "demand")
    private Payment payment;

    @OneToMany(mappedBy = "id.demand", cascade = CascadeType.ALL)
    private Set<DemandItem> items = emptySet();

    private Integer status;

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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<DemandItem> getItems() {
        return items;
    }

    public void setItems(Set<DemandItem> items) {
        this.items = items;
    }

    public DemandStatus getStatus() {
        return DemandStatus.of(status);
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
