package com.github.nicolasholanda.debt.utils;

import com.github.nicolasholanda.debt.model.Address;

import java.math.BigDecimal;

import static java.lang.Math.*;

public class AddressUtils {

    public static BigDecimal distanceInKm(Address store, Address customer) {
        var slat = store.getLatitude().doubleValue();
        var slon = store.getLongitude().doubleValue();
        var clat = customer.getLatitude().doubleValue();
        var clon = customer.getLongitude().doubleValue();

        return BigDecimal.valueOf(6371 * acos( cos(toRadians(slat)) * cos(toRadians(clat)) * cos(toRadians(clon) - toRadians(slon)) + sin(toRadians(slat)) * sin(toRadians(clat)) ));
    }
}
