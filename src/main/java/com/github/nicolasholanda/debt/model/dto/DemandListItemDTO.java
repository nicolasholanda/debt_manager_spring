package com.github.nicolasholanda.debt.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.nicolasholanda.debt.model.Demand;

import java.util.Date;

public class DemandListItemDTO extends BaseDTO<Integer> {

    private String storeName;
    private Integer customerId;
    private Integer status;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date shippedDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date requestDate;

    public DemandListItemDTO(Demand demand) {
        super(demand.getId());
        status = demand.getStatus().getCode();
        storeName = demand.getStore().getName();
        customerId = demand.getCustomer().getId();
        shippedDate = demand.getShippedDate();
        requestDate = demand.getRequestDate();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
