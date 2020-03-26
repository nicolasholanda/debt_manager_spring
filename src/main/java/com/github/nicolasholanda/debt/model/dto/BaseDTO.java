package com.github.nicolasholanda.debt.model.dto;

import java.io.Serializable;

public abstract class BaseDTO<I extends Number> implements Serializable {

    private static final long serialVersionUID = 1L;

    private I id;

    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }

    public BaseDTO() {
    }

    public BaseDTO(I id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseDTO<I> that = (BaseDTO<I>) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);
    }
}
