package com.github.nicolasholanda.debt.model.dto;

import com.github.nicolasholanda.debt.model.Brand;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class NewStoreDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "{store.owner.notnull}")
    private Integer ownerId;

    @Size(min = 1, max = 100)
    @NotNull(message = "{store.name.notnull}")
    private String name;

    @NotNull(message = "{store.address.notnull}")
    private Integer addressId;

    @NotEmpty(message = "{store.brands.notempty}")
    @NotNull(message = "{store.brands.notnull}")
    private List<Brand> brands;

    public NewStoreDTO(){
    }

    public NewStoreDTO(Integer owner, String name, Integer addressId, List<Brand> brands) {
        this.name = name;
        this.ownerId = owner;
        this.addressId = addressId;
        this.brands = brands;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }
}
